package com.cheung.tim.output.model;

import java.util.ArrayList;
import java.util.List;

public class LibrarySubmission {

    private Integer libraryNum;
    private Integer booksForScanning;
    private ArrayList<Integer> books;

    public LibrarySubmission(Integer libraryNum, Integer booksForScanning){
        this.libraryNum = libraryNum;
        this.booksForScanning = booksForScanning;
        this.books = new ArrayList<>();
    }

    public void addBook(Integer ride){
        this.books.add(ride);
    }

    public String getBooks(){
        StringBuilder outBooks = new StringBuilder();
        for (Integer i :this.books){
            outBooks.append(i);
            outBooks.append(" ");
        }
        if(this.books.size() > 0){
            outBooks.setLength(outBooks.length()-1);
        }
        return outBooks.toString();
    }

    public int getNumBooks(){
        return books.size();
    }

    public Integer getLibrary(){
        return this.libraryNum;
    }
}
