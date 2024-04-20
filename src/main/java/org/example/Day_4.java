package org.example;

import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day_4 {
    public static int calcPoints(String line) {
        String match = line.split(":")[1];
        String[] input = match.split("\\|");
        String[] winning = input[0].split(" ");
        HashSet<Integer> winningCards = new HashSet<>();
        for (String num : winning) {
            if (num.length() > 0) winningCards.add(Integer.parseInt(num));
        }
        int sum = 0;
        String[] ownedCards = input[1].split(" ");
        for (String owned : input[1].split(" ")) {
            if (owned.length() > 0 && winningCards.contains(Integer.parseInt(owned))) {
                if(sum == 0) sum = 1;
                else sum <<=1;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = Utils.readInputLines("scratchpaper");
        int sum = 0;
        for (String line : lines) {
            sum += calcPoints(line);
        }
        System.out.println(sum);
    }
}
