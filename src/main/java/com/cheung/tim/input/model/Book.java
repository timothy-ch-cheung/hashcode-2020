package com.cheung.tim.input.model;

public class Book {
    public Integer bookID;
    public Integer score;

    public Book(Integer bookID, Integer score){
        this.bookID = bookID;
        this.score = score;
    }

    public Integer getScore(){
        return score;
    }
}
