package org.example.day_1;

import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_2 {
    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = Utils.readInputLines("cubeConundrum");
        int sum = 0;
        HashMap<String, Integer> limits = new HashMap<>() {{
            put("red", 12);
            put("green", 13);
            put("blue", 14);
        }};
        for (String line : lines) {
            String[] initial = line.split(":");
            int gameId = Integer.parseInt(initial[0].split(" ")[1]);
            String regex = "(\\d+) (\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(initial[1]);
            boolean flag = true;
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                String word = matcher.group(2);
                if (limits.get(word) < number) {
                    flag = false;
                    break;
                }
            }
            if (flag) sum += gameId;
        }
        System.out.println(sum);
    }
}
