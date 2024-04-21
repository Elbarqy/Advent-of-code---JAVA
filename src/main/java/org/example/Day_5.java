package org.example;

import org.example.utils.Utils;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Day_5 {
    public static void main(String[] args) throws IOException {
        String content = Utils.getResourceFileAsString("inputs/fertilizer");
        var blocks = content.split("\n\n");
        long[] seeds = Arrays.stream(blocks[0].split(":")[1].split(" ")).filter(s -> !Objects.equals(s, "")).mapToLong(Long::parseLong).toArray();
        HashMap<String, List<long[]>> mapper = new HashMap<>();
        Arrays.stream(Arrays.copyOfRange(blocks, 1, blocks.length))
                .forEach(block -> {
                    String[] lines = block.split("\n");
                    String key = lines[0].split(" map:")[0];
                    List<long[]> ranges = Arrays.stream(Arrays.copyOfRange(lines, 1, lines.length))
                            .map(line -> Arrays.stream(line.split(" "))
                                    .filter(e -> !Objects.equals(e, ""))
                                    .mapToLong(Long::parseLong).toArray()
                            ).toList();
                    mapper.put(key, ranges);
                });
        String[] keys = new String[]{
                "seed-to-soil",
                "soil-to-fertilizer",
                "fertilizer-to-water",
                "water-to-light",
                "light-to-temperature",
                "temperature-to-humidity",
                "humidity-to-location"
        };
        long[] min = {Long.MAX_VALUE};
        Arrays.stream(seeds).forEach(seed -> {
            AtomicLong currVal = new AtomicLong(seed);
            Arrays.stream(keys).forEach(key -> {
                ArrayList<long[]> validRange = new ArrayList<>();
                mapper.get(key).stream().filter(range -> currVal.get() >= range[1] && currVal.get() <= range[1] + range[2]).forEach(validRange::add);
                if (!validRange.isEmpty()) {
                    currVal.addAndGet(-1 * validRange.get(0)[1] + validRange.get(0)[0]);
                }
            });
            min[0] = Math.min(min[0], currVal.longValue());
        });
        System.out.println(min[0]);
    }
}
