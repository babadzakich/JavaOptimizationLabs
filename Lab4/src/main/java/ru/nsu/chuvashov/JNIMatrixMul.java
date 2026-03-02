package ru.nsu.chuvashov;

public class JNIMatrixMul {
    static {
        System.loadLibrary("matrixmul_O3");
    }

    public native double[] multiply(double[] a, double[] b, int n);
}
