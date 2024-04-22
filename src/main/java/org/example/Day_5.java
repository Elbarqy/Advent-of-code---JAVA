package org.example;

import org.example.utils.Utils;

import java.io.*;
import java.util.*;

public class Day_5 {
    public static long[] getOverlapRange(long[] originalRange, long[] mappedRange) {
        long mEnd = mappedRange[0] + mappedRange[1] - 1, end = originalRange[0] + originalRange[1] - 1;
        long overlapStart = Math.max(originalRange[0], mappedRange[0]), overlapEnd = Math.min(mEnd, end);
        if (overlapStart <= overlapEnd) {
            return new long[]{overlapStart, overlapEnd - overlapStart + 1};
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String content = Utils.getResourceFileAsString("inputs/fertilizer");
        var blocks = content.split("\n\n");
        long[] seeds = Arrays.stream(blocks[0].split(":")[1].split(" ")).filter(s -> !Objects.equals(s, "")).mapToLong(Long::parseLong).toArray();
        long[][] seedRanges = new long[seeds.length / 2][2];
        for (int i = 0; i < seeds.length / 2; ++i) {
            int newIdx = i * 2;
            seedRanges[i] = new long[]{seeds[newIdx], seeds[newIdx + 1]};
        }
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

        Stack<long[]> ranges = new Stack<>();
        Arrays.stream(seedRanges).forEach(seedRange -> {
            ranges.push(seedRange);
            Arrays.stream(keys).forEach(key -> {
                ArrayList<long[]> generatedRanges = new ArrayList<>();
                mapper.get(key).forEach(mappedRange -> {
                    while (!ranges.isEmpty()) {
                        long[] originalRange = ranges.pop();
                        long[] overlap = getOverlapRange(originalRange, new long[]{mappedRange[1], mappedRange[2]});
                        if (overlap != null) {
                            long diff = mappedRange[0] - mappedRange[1];
                            generatedRanges.add(new long[]{
                                    overlap[0] + diff, overlap[1]
                            });
                            if (originalRange[0] < overlap[0]) {
                                generatedRanges.add(new long[]{
                                        originalRange[0],
                                        overlap[0] - originalRange[0]
                                });
                            }
                            long end = originalRange[0] + originalRange[1] - 1;
                            if (end > overlap[0] + overlap[1] - 1) {
                                long temp = overlap[0] + overlap[1];
                                generatedRanges.add(new long[]{
                                        overlap[0] + overlap[1], end - temp - 1
                                });
                            }
                        } else {
                            generatedRanges.add(originalRange);
                        }
                    }
                    ranges.addAll(generatedRanges);
                });
            });
            ranges.forEach(range -> {
                if (min[0] > range[0]) min[0] = range[0];
            });
            ranges.clear();
        });
        System.out.println(min[0]);
    }
}
