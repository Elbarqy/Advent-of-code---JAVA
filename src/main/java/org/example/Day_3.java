package org.example;

import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class Day_3 {
    public static boolean isAdjacentToSymbol(String[] arr, int i, int j, int sz, int lineSz) {
        int[] iPlus = new int[]{0, 0, 1, 1, -1, -1, 1, -1};
        int[] jPlus = new int[]{1, -1, 1, -1, -1, 1, 0, 0};
        for (int idx = 0; idx < 8; ++idx) {
            int newI = iPlus[idx] + i;
            int newJ = jPlus[idx] + j;
            if (Character.isDigit(arr[i].charAt(j)) && newI > 0 && newJ > 0 && newI < sz && newJ < lineSz && !Character.isDigit(arr[newI].charAt(newJ)) && arr[newI].charAt(newJ) != '.') {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> fs = Utils.readInputLines("gearRatio");
        String[] lines = fs.toArray(new String[0]);
        int sum = 0, sz = lines.length, lineSz = lines[0].length();
        for (int i = 0; i < sz; ++i) {
            for (int j = 0; j < lineSz; ++j) {
                if (isAdjacentToSymbol(lines, i, j, sz, lineSz)) {
                    int l = j, r = j;
                    while (l > 0 && Character.isDigit(lines[i].charAt(l))) --l;
                    while (r < lineSz - 1 && Character.isDigit(lines[i].charAt(r))) ++r;
                    if (!Character.isDigit(lines[i].charAt(l))) ++l;
                    if (!Character.isDigit(lines[i].charAt(r))) --r;
                    String x = lines[i].substring(l, r + 1);
                    int adder = Integer.parseInt(x);
                    sum += adder;
                    j = r;
                }
            }
        }
        System.out.println(sum);
    }
}
