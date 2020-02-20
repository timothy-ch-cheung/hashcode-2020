package com.cheung.tim.input.model;

import com.cheung.tim.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Library implements Type {
    public Map<String, Integer> data;
    public HashSet<Integer> books;

    public Library(Map<String, Integer> data, String ... books) {
        this.data = data;
        this.books = new HashSet<>();
        for(String book: books){
            this.books.add(Integer.valueOf(book));
        }

    }

    String name;

    @Override
    public String getName() {
        return this.name;
    }
}
