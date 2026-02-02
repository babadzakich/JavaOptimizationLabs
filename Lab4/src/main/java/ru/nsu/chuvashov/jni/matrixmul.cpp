#include "ru_nsu_chuvashov_JNIMatrixMul.h"

JNIEXPORT jdoubleArray JNICALL Java_ru_nsu_chuvashov_JNIMatrixMul_multiply
  (JNIEnv *env, jobject thisObject, jdoubleArray arr1, jdoubleArray arr2, jint size) {
    jsize len = size;
    jdouble *matrix1 = env->GetDoubleArrayElements(arr1, nullptr);
    jdouble *matrix2 = env->GetDoubleArrayElements(arr2, nullptr);
    jdoubleArray result = env->NewDoubleArray(len * len);
    jdouble *matrixResult = new jdouble[len * len];
    for (jsize i = 0; i < len; i++) {
        for (jsize j = 0; j < len; j++) {
            matrixResult[i * len + j] = 0;
            for (jsize k = 0; k < len; k++) {
                matrixResult[i * len + j] += matrix1[i * len + k] * matrix2[k * len + j];
            }
        }
    }
    env->SetDoubleArrayRegion(result, 0, len * len, matrixResult);
    env->ReleaseDoubleArrayElements(arr1, matrix1, 0);
    env->ReleaseDoubleArrayElements(arr2, matrix2, 0);
    delete[] matrixResult;
    return result;
  }