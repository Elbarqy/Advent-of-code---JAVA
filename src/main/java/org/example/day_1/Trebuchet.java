package org.example.day_1;

import org.example.utils.Utils;

import java.util.ArrayList;

public class Trebuchet {
    public static void main(String[] args) {
        try {
            ArrayList<String> inputLines = Utils.readInputLines("trebuchet");
            int sum = 0;
            for (String line : inputLines) {
                Character r = null, l = null;
                int sz = line.length();
                for (int i = 0; i < sz; ++i) {
                    if (Character.isDigit(line.charAt(i))) {
                        char digit = line.charAt(i);
                        if (l == null) l = r = digit;
                        else{
                            r = digit;
                        }
                    }
                }
                sum += Integer.parseInt(String.format("%c%c", l, r));
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
