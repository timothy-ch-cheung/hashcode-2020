package com.cheung.tim;

import com.cheung.tim.input.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashCodeFileParser {

    public static LibraryCollection read(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        Map<String, Integer> headers = parseRow(reader.readLine(), InputHeading.values());
        int library_num = headers.get(InputHeading.library_num);

        HashMap <Integer, Library> libraries = new HashMap<>();
        String line;
        for(int i = 0; i < library_num; i++){
            line = reader.readLine();
            HashMap<String, Integer> libraryData = (HashMap<String, Integer>) parseRow(line, LibraryHeading.values());
            line = reader.readLine();
            String[] books = line.split(" ");
            libraries.put(i, new Library(libraryData, books));
        }
        LibraryCollection libraryCollection = new LibraryCollection();
        libraryCollection.bookNum = headers.get(InputHeading.book_num);
        libraryCollection.daysForScanning = headers.get(InputHeading.scanning_time);
        libraryCollection.libraries = libraries;
        return libraryCollection;
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
