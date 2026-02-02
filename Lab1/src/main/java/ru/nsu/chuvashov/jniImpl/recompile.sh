export JAVA_HOME="/usr/lib/jvm/java-21-openjdk-amd64"
g++ -shared -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -o libmyjni.so myLib.cpp