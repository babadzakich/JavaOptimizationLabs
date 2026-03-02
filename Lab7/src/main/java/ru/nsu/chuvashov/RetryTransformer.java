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

    private void transformMethod(CtMethod method, CtClass cc) throws Exception {
        String methodName = method.getName();
        CtClass returnType = method.getReturnType();

        StringBuilder newBody = new StringBuilder();
        newBody.append("{\n");
        newBody.append("  long startTime = System.currentTimeMillis();\n");
        newBody.append("  System.out.println(\"[Agent] >>> Start: ").append(methodName).append(" Args: \" + java.util.Arrays.toString($args));\n");
        newBody.append("  int attempts = 0;\n");
        newBody.append("  Throwable lastError = null;\n");
        newBody.append("  \n");
        newBody.append("  while (attempts < 3) {\n");
        newBody.append("    try {\n");

        if (returnType == CtClass.voidType) {
            newBody.append("      {\n");
            newBody.append("        System.out.println(\"   (Inside Method) Processing: \" + $1);\n");
            newBody.append("        if (new java.util.Random().nextInt(10) < 7) {\n");
            newBody.append("          throw new RuntimeException(\"Something went wrong!\");\n");
            newBody.append("        }\n");
            newBody.append("      }\n");
            newBody.append("      long endTime = System.currentTimeMillis();\n");
            newBody.append("      System.out.println(\"[Agent] <<< Success: ").append(methodName).append(" Time: \" + (endTime - startTime) + \"ms\");\n");
            newBody.append("      return;\n");
        } else {
            newBody.append("      ").append(returnType.getName()).append(" result;\n");
            newBody.append("      {\n");
            newBody.append("        System.out.println(\"   (Inside Method) Processing: \" + $1);\n");
            newBody.append("        if (new java.util.Random().nextInt(10) < 7) {\n");
            newBody.append("          throw new RuntimeException(\"Something went wrong!\");\n");
            newBody.append("        }\n");
            newBody.append("        result = \"Processed-\" + $1;\n");
            newBody.append("      }\n");
            newBody.append("      long endTime = System.currentTimeMillis();\n");
            newBody.append("      System.out.println(\"[Agent] <<< Success: ").append(methodName).append(" Time: \" + (endTime - startTime) + \"ms\");\n");
            newBody.append("      return result;\n");
        }

        newBody.append("    } catch (Throwable e) {\n");
        newBody.append("      attempts++;\n");
        newBody.append("      lastError = e;\n");
        newBody.append("      System.out.println(\"[Agent] !!! Exception in ").append(methodName).append(". Retry #\" + attempts);\n");
        newBody.append("      if (attempts >= 3) {\n");
        newBody.append("        System.out.println(\"[Agent] Failed after 3 attempts\");\n");
        newBody.append("        throw lastError;\n");
        newBody.append("      }\n");
        newBody.append("    }\n");
        newBody.append("  }\n");
        newBody.append("  throw new RuntimeException(\"Unreachable\");\n");
        newBody.append("}\n");

        method.setBody(newBody.toString());
    }
}
