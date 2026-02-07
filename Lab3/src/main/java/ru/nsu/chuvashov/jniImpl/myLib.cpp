#include "ru_nsu_chuvashov_MyJNIClass.h"
#include <iostream>
#include <cstring>
#include <cstdio>
#include <cstdlib>

using namespace std;

struct nativeStruct
{
    int intField;
    double doubleField;
};

// Forward declarations for recursive calls
void step5Function(int result);
void step4Function(int result);
void step3Function(int depth, int current);
void step2Function(int depth);
void step1Function(int depth);

void step5Function(int result) {
    cout << "Step 5: Deep inside the call stack..." << endl;
    char buffer[50];
    snprintf(buffer, sizeof(buffer), "Final value: %d", result);
    cout << buffer << endl;
    
    cout << "Step 6: About to brick..." << endl;
    int* nullPtr = nullptr;
    *nullPtr = 123;
    
    cout << "Step 7: This will never execute" << endl;
}

void step4Function(int result) {
    cout << "Step 4: Processing result..." << endl;
    result = result * 2;
    cout << "Doubled result: " << result << endl;
    step5Function(result);
    cout << "Returned from step5 (never happens)" << endl;
}

void step3Function(int depth, int current) {
    cout << "Step 3: Performing calculations (iteration " << current << ")..." << endl;
    if (current >= depth) {
        int result = current * current;
        cout << "Calculation complete: " << result << endl;
        step4Function(result);
        return;
    }
    step3Function(depth, current + 1);
}

void step2Function(int depth) {
    cout << "Step 2: Creating native struct..." << endl;
    nativeStruct* ns = new nativeStruct();
    ns->intField = 42;
    ns->doubleField = 3.14159;
    cout << "Struct created with intField=" << ns->intField << endl;
    
    step3Function(depth, 0);
    
    delete ns;
}

void step1Function(int depth) {
    cout << "Step 1: Allocating memory..." << endl;
    void* ptr1 = malloc(1024);
    memset(ptr1, 0, 1024);
    cout << "Memory allocated" << endl;
    
    step2Function(depth);
    
    // This cleanup never happens due to crash
    free(ptr1);
}

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    devourMemoryAndKMS
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_devourMemoryAndKMS
  (JNIEnv *env, jobject thisObj) {
    cout << "Devouring memory and KMS..." << endl;
    int count = 0;
    while (true) {
        void* ptr = malloc(1024 * 1024); // Allocate 1 MB
        if (ptr == nullptr) {
            cout << "Memory allocation failed after " << count << " MB" << endl;
            break;
        }
        count++;
        memset(ptr, 1, 1024 * 1024);
        if (count % 100 == 0) {
            cout << "Allocated " << count << " MB so far..." << endl;
        }
    }
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    allocate1kb
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_allocate1kb
  (JNIEnv *env, jobject thisObj) {
    void* ptr = malloc(1024); // Allocate 1 KB
    if (ptr != nullptr) {
        memset(ptr, 1, 1024);
        cout << "Allocated 1 KB of memory at " << ptr << endl;
    } else {
        cout << "Failed to allocate 1 KB of memory." << endl;
    }
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    brickInside
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_brickInside
  (JNIEnv *env, jobject thisObj, jint depth) {
    cout << "=== Starting brickInside with depth=" << depth << " ===" << endl;
    step1Function(depth);
    cout << "=== Finished brickInside (never happens) ===" << endl;
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    stringLength
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_ru_nsu_chuvashov_MyJNIClass_stringLength
  (JNIEnv *env, jobject thisObj, jstring text) {
    if (text == nullptr) {
        return 0;
    }
    const char* str = env->GetStringUTFChars(text, nullptr);
    if (str == nullptr) {
        return 0; // Out of memory
    }
    jint length = static_cast<jint>(strlen(str));
    env->ReleaseStringUTFChars(text, str);
    return length;
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    callNigger
 * Signature: (Lru/nsu/chuvashov/SimpleBean;)V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_callNigger
  (JNIEnv *env, jobject thisObj, jobject simpleBean) {
    if (simpleBean == nullptr) {
        return;
    }
    jclass simpleBeanClass = env->GetObjectClass(simpleBean);
    jmethodID getValueMethod = env->GetMethodID(simpleBeanClass, "sayShit", "()V");
    if (getValueMethod != nullptr) {
        env->CallVoidMethod(simpleBean, getValueMethod);
    }
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    changeCottonField
 * Signature: (Lru/nsu/chuvashov/SimpleBean;I)V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_changeCottonField
  (JNIEnv *env, jobject thisObj, jobject simpleBean, jint newValue) {
    jclass simpleBeanClass = env->GetObjectClass(simpleBean);
    jfieldID cottonFieldID = env->GetFieldID(simpleBeanClass, "id", "I");
    if (cottonFieldID != nullptr) {
        env->SetIntField(simpleBean, cottonFieldID, newValue);
    }
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    allocSwag
 * Signature: (ID)J
 */
JNIEXPORT jlong JNICALL Java_ru_nsu_chuvashov_MyJNIClass_allocSwag
  (JNIEnv *env, jobject thisObj, jint intVal, jdouble doubleVal) {
    nativeStruct* ns = new nativeStruct();
    ns->intField = intVal; 
    ns->doubleField = doubleVal;
    return reinterpret_cast<jlong>(ns);
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    readSwag
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_ru_nsu_chuvashov_MyJNIClass_readSwag
  (JNIEnv *env, jobject thisObj, jlong ptr) {
    nativeStruct* ns = reinterpret_cast<nativeStruct*>(ptr);
    if (ns == nullptr) {
        return env->NewStringUTF("");
    }
    char buffer[100];
    snprintf(buffer, sizeof(buffer), "intField: %d, doubleField: %f", ns->intField, ns->doubleField);
    return env->NewStringUTF(buffer);
  }

/*
 * Class:     ru_nsu_chuvashov_MyJNIClass
 * Method:    freeSwag
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_ru_nsu_chuvashov_MyJNIClass_freeSwag
  (JNIEnv *env, jobject thisObj, jlong ptr) {
    nativeStruct* ns = reinterpret_cast<nativeStruct*>(ptr);
    delete ns;
  }