package org.example;

import org.example.utils.Utils;

import java.io.IOException;
import java.util.*;

public class Day_3 {
    public static HashMap<String, Integer> getAdjacentNumbers(String[] arr, int i, int j, int sz, int lineSz) {
        HashMap<String, Integer> result = new HashMap<>();
        int[] iPlus = new int[]{0, 0, 1, 1, 1, -1, -1, -1};
        int[] jPlus = new int[]{1, -1, -1, 0, 1, -1, 0, 1};
        HashMap<Integer, ArrayList<Integer>> indices = new HashMap<>();
        if (arr[i].charAt(j) == '*') for (int idx = 0; idx < 8; ++idx) {
            int newI = iPlus[idx] + i;
            int newJ = jPlus[idx] + j;
            if (newI >= 0 && newJ >= 0 && newI < sz && newJ < lineSz && Character.isDigit(arr[newI].charAt(newJ)) && arr[newI].charAt(newJ) != '.') {
                int[] boundaries = getNumberBoundariesAt(arr, newI, newJ, lineSz);
                result.put(
                        newI + "," + boundaries[0], boundaries[1]
                );
            }
        }
        return result;
    }

    public static int[] getNumberBoundariesAt(String[] arr, int i, int j, int lineSz) {
        int l = j, r = j;
        while (l > 0 && Character.isDigit(arr[i].charAt(l))) --l;
        while (r < lineSz - 1 && Character.isDigit(arr[i].charAt(r))) ++r;
        if (!Character.isDigit(arr[i].charAt(l))) ++l;
        if (!Character.isDigit(arr[i].charAt(r))) --r;
        return new int[]{l, r + 1};
    }


    public static void main(String[] args) throws IOException {
        ArrayList<String> fs = Utils.readInputLines("gearRatio");
        String[] lines = fs.toArray(new String[0]);
        int sum = 0, sz = lines.length, lineSz = lines[0].length();
        for (int i = 0; i < sz; ++i) {
            for (int j = 0; j < lineSz; ++j) {
                var result = getAdjacentNumbers(lines, i, j, sz, lineSz);
                if (result.size() == 2) {
                    ArrayList<Integer> nums = new ArrayList<>();
                    for (var entry : result.entrySet()) {
                        String[] pos = entry.getKey().split(",");
                        String x = lines[Integer.parseInt(pos[0])].substring(Integer.parseInt(pos[1]), entry.getValue());
                        nums.add(Integer.parseInt(x));
                    }
                    sum += nums.stream().reduce(1, (a, b) -> a * b);
                }
            }
        }
        System.out.println(sum);
    }
}
