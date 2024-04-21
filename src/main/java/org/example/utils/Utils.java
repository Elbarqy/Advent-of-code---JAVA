package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Utils {
    public static ArrayList<String> readInputLines(String fileName) throws NullPointerException, IOException {
        InputStream inputStream = Utils.class.getResourceAsStream("/inputs/"+fileName);
        ArrayList<String> res = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            res.add(line);
        }
        return res;
    }

    public static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
