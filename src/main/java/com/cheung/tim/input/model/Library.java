package com.cheung.tim.input.model;

import com.cheung.tim.Type;

import java.util.HashSet;
import java.util.Map;

public class Library implements Type {
    public Integer libraryNum;
    public Map<String, Integer> data;
    public HashSet<Integer> books;

    public Library(Map<String, Integer> data, Integer libraryNum, String... books) {
        this.data = data;
        this.books = new HashSet<>();
        for (String book : books) {
            this.books.add(Integer.valueOf(book));
        }

    }

    String name;

    @Override
    public String getName() {
        return this.name;
    }
}
