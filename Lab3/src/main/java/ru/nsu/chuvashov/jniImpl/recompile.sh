export JAVA_HOME="/usr/lib/jvm/java-21-openjdk"
g++ -shared -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -o libmyjni.so myLib.cpp