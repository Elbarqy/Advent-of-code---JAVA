package org.example;

import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Tuple {
    String key;
    char value;

    public Tuple(String key, char value) {
        this.key = key;
        this.value = value;
    }
}

public class Day_1 {
    public static void main(String[] args) {
        try {
            ArrayList<String> inputLines = Utils.readInputLines("trebuchet");
            int sum = 0;
            for (String line : inputLines) {
                Character r = null, l = null;
                int sz = line.length();
                Map<Character, Tuple[]> dict = new HashMap<>() {{
                    put('o', new Tuple[]{new Tuple("one", '1')});
                    put('t', new Tuple[]{new Tuple("two", '2'), new Tuple("three", '3')});
                    put('f', new Tuple[]{new Tuple("four", '4'), new Tuple("five", '5')});
                    put('s', new Tuple[]{new Tuple("six", '6'), new Tuple("seven", '7')});
                    put('e', new Tuple[]{new Tuple("eight", '8')});
                    put('n', new Tuple[]{new Tuple("nine", '9')});
                }};
                for (int i = 0; i < sz; ++i) {
                    Character digit = null;
                    if (Character.isDigit(line.charAt(i))) digit = line.charAt(i);
                    else {
                        if (dict.containsKey(line.charAt(i))) {
                            for (Tuple candidate : dict.get(line.charAt(i))) {
                                int canSz = candidate.key.length();
                                int limit = i + canSz - 1;
                                if (limit >= sz) continue;
                                boolean isMatched = true;
                                for (int j = i; j <= limit; ++j) {
                                    if (Character.compare(candidate.key.charAt(j - i), line.charAt(j)) != 0) {
                                        isMatched = false;
                                        break;
                                    }
                                }
                                if (isMatched) {
                                    digit = candidate.value;
                                    i = limit - 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (digit != null) {
                        if (l == null) l = r = digit;
                        else {
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
