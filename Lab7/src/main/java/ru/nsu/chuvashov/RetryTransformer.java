package ru.nsu.chuvashov;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;

import javassist.*;

public class RetryTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            java.security.ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        String dotClassName = className.replace('/', '.');

        try {
            ClassPool classPool = ClassPool.getDefault();
            classPool.insertClassPath(new LoaderClassPath(loader));

            CtClass cc = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            boolean isModified = false;

            for (CtMethod method : cc.getDeclaredMethods()) {
                if (method.hasAnnotation(Reliable.class)) {
                    System.out.println("[Transformer] Modifying method: " + method.getName() + " in class: " + dotClassName);
                    transformMethod(method, cc);
                    isModified = true;
                }
            }
            if (isModified) {
                return cc.toBytecode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void transformMethod(CtMethod method, CtClass cc) throws CannotCompileException, NotFoundException {
        String oldName = method.getName();
        String newName = oldName + "$impl";

        method.setName(newName);

        CtMethod newMethod = CtNewMethod.copy(method, oldName, cc, null);

        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("  long startTime = System.currentTimeMillis();");
        body.append("  System.out.println(\"[Agent] >>> Start: ").append(oldName).append(" Args: \" + java.util.Arrays.toString($args));");
        body.append("  int attempts = 0;");
        body.append("  Throwable lastError = null;");

        body.append("  while (attempts < 3) {");
        body.append("    try {");
        body.append("       Object result = ").append(newName).append("($$);"); // Вызов оригинала
        body.append("       long endTime = System.currentTimeMillis();");
        body.append("       System.out.println(\"[Agent] <<< Success: ").append(oldName).append(" Time: \" + (endTime - startTime) + \"ms\");");
        body.append("       return ($r) result;");
        body.append("    } catch (Throwable e) {");
        body.append("       attempts++;");
        body.append("       lastError = e;");
        body.append("       System.out.println(\"[Agent] !!! Exception in ").append(oldName).append(". Retry #\" + attempts);");
        body.append("    }");
        body.append("  }");

        body.append("  System.out.println(\"[Agent] Failed after 3 attempts\");");
        body.append("  throw lastError;");
        body.append("}");

        newMethod.setBody(body.toString());
        cc.addMethod(newMethod);
    }
}
