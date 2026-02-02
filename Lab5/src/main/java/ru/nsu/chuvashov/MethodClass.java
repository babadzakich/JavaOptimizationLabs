package ru.nsu.chuvashov;

import java.util.List;

public class MethodClass {
    static public int len(String str) {
        return str.length();
    }

    static int getCompareClassValue(CompareClass obj) {
        return obj.getValue();
    }

    static public void setCompareClassValue(CompareClass clss, int a) {
        clss.value = a;
    }
    public static void bubbleSort(List<CompareClass> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getValue() > list.get(j + 1).getValue()) {
                    // swap list[j] and list[j+1]
                    CompareClass temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
