package org.example;

import org.example.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day_4 {
    public static int calcPoints(String line, HashMap<Integer, Integer> copies) {
        String[] identifiers = line.split(":");
        String match = identifiers[1];
        String[] temp = identifiers[0].split(" ");
        int cardId = Integer.parseInt(temp[temp.length - 1]);
        String[] input = match.split("\\|");
        String[] winning = input[0].split(" ");
        HashSet<Integer> winningCards = new HashSet<>();
        for (String num : winning) {
            if (!num.isEmpty()) winningCards.add(Integer.parseInt(num));
        }
        String[] ownedCards = input[1].split(" ");
        int count = 0;
        for (String owned : input[1].split(" ")) {
            if (!owned.isEmpty() && winningCards.contains(Integer.parseInt(owned))) {
                ++count;
            }
        }
        int currCopies = copies.getOrDefault(cardId, 0);
        ++currCopies;
        copies.put(cardId, currCopies);
        for (int i = 1; i <= count; ++i) {
            int val = copies.getOrDefault(cardId + i, 0);
            copies.put(cardId + i, (currCopies + val));
        }
        return cardId;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = Utils.readInputLines("scratchpaper");
        int last = 1;
        HashMap<Integer, Integer> copies = new HashMap<>();
        for (String line : lines) {
            last = calcPoints(line, copies);
        }
        Long sum = 0L;
        for (var entry : copies.entrySet()) {
            if (entry.getKey() <= last) {
                sum += entry.getValue();
            }
        }
        System.out.println(sum);
    }
}
