-injars app.jar
-outjars app_nothing.jar
-libraryjars <java.home>/jmods/java.base.jmod

-keep class ru.nsu.chuvashov.Main { public static void main(java.lang.String[]); }
-keep class ru.nsu.chuvashov.Ghost2 { *; }
-keepclassmembers class ru.nsu.chuvashov.Ghost1 {
    public void unused1();
}