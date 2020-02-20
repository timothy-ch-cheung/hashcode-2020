package com.cheung.tim.input.model;

import com.cheung.tim.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Library implements Type {
    public Integer libraryNum;
    public Map<String, Integer> data;
    public ArrayList<Integer> books;
    public Integer score;

    public Library(Map<String, Integer> data, Integer libraryNum, ArrayList<Integer> books) {
        this.libraryNum = libraryNum;
        this.data = data;
        this.books = books;
    }

    String name;

    public Integer getSignUpTime(){
        return data.get(LibraryHeading.signup_time.toString());
    }

    public Integer getScore(){
        return score;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
