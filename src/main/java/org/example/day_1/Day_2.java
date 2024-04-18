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
        for (String line : lines) {
            String[] initial = line.split(":");
            int gameId = Integer.parseInt(initial[0].split(" ")[1]);
            String regex = "(\\d+) (\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(initial[1]);
            HashMap<String, Integer> mapCount = new HashMap<>();
            String[] items = new String[]{"red", "green", "blue"};
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                String word = matcher.group(2);
                int currCount = mapCount.getOrDefault(word, 0);
                mapCount.put(word, Math.max(currCount, number));
            }
            int mul = 1;
            for(String item: items){
                mul *= mapCount.getOrDefault(item, 0);
            }
            sum += mul;
        }
        System.out.println(sum);
    }
}
