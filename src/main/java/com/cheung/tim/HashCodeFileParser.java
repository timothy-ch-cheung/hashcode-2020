package com.cheung.tim;

import com.cheung.tim.input.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HashCodeFileParser {

    public static LibraryCollection read(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        Map<String, Integer> headers = parseRow(reader.readLine(), InputHeading.values());
        int library_num = headers.get(InputHeading.library_num.toString());

        String[] bookScores = reader.readLine().split(" ");

        HashMap<Integer, Integer> scores = new HashMap<>();
        for (int i = 0; i < scores.size(); i++) {
            scores.put(i, Integer.valueOf(bookScores[i]));
        }

        HashMap<Integer, Library> libraries = new HashMap<>();
        String line;
        for (int i = 0; i < library_num; i++) {
            line = reader.readLine();
            HashMap<String, Integer> libraryData = (HashMap<String, Integer>) parseRow(line, LibraryHeading.values());
            line = reader.readLine();
            String[] books = line.split(" ");
            ArrayList<Book> outBooks = new ArrayList<>();
            for(String b: books){
                outBooks.add(new Book(Integer.valueOf(b), scores.get(b)));
            }
            Collections.sort(outBooks,Comparator.comparingInt(Book::getScore));
            ArrayList<Integer> outputBooks = new ArrayList<>();
            for(Book b: outBooks){
                outputBooks.add(b.bookID);
            }
            libraries.put(i, new Library(libraryData, i, outputBooks));
        }

        LibraryCollection libraryCollection = new LibraryCollection();
        libraryCollection.bookNum = headers.get(InputHeading.book_num);
        libraryCollection.daysForScanning = headers.get(InputHeading.scanning_time);
        libraryCollection.libraries = libraries;
        libraryCollection.booksScores = scores;
        return libraryCollection;
    }

    private static Map<String, Integer> parseRow(String line, Type[] types) {
        Map<String, Integer> headers = new HashMap<>();
        String[] elements = line.split(" ");
        int fail = 0;
        try {
            for (int i = 0; i < types.length; i++) {
                fail = i;
                headers.put(types[i].getName(), Integer.valueOf(elements[i]));
            }
        } catch (Exception e) {
            System.out.println(fail);
            System.out.println(Arrays.toString(elements));
            System.out.println(line);
            throw e;
        }
        return headers;
    }
}
