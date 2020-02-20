package com.cheung.tim;

import com.cheung.tim.input.model.InputHeading;
import com.cheung.tim.input.model.Rides;
import com.cheung.tim.input.model.TaxiRank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashCodeFileParser {

    public static TaxiRank read(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        Map<String, Integer> headers = parseRow(reader.readLine(), InputHeading.values());
        List<Map<String, Integer>> rides = new ArrayList();

        String line;
        int id = 0;
        while ((line = reader.readLine()) != null) {
            Map row = parseRow(line, Rides.values());
            row.put("id",id);
            rides.add(row);
            id++;
        }
        return new TaxiRank(headers, rides);
    }

    private static Map<String, Integer> parseRow(String line, Type[] types) {
        Map<String, Integer> headers = new HashMap<>();
        String[] elements = line.split(" ");
        for (int i = 0; i < types.length; i++) {
            headers.put(types[i].getName(), Integer.valueOf(elements[i]));
        }
        return headers;
    }
}
