package org.example.utils;

import java.io.*;
import java.util.ArrayList;

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
}
