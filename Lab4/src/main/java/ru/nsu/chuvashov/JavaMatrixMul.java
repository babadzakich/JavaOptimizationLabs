package ru.nsu.chuvashov;

public class JavaMatrixMul {
    public static double[] multiply(double[] a, double[] b, int n) {
        double[] result = new double[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i*n + j] += a[i*n + k] * b[k*n + j];
                }
            }
        }
        return result;
    }
}
