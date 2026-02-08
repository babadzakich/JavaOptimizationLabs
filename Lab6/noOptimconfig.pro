-injars app.jar
-outjars app_nothing.jar
-libraryjars <java.home>/jmods/java.base.jmod

-dontshrink
-dontoptimize
-dontobfuscate
-keep class ru.nsu.chuvashov.Main { public static void main(java.lang.String[]); }
-keep class ru.nsu.chuvashov.Ghost* { *; }