package com.cheung.tim.input.model;

import com.cheung.tim.Type;

public enum LibraryHeading implements Type {
    book_num("book_num"),
    signup_time("signup_time"),
    books_per_day("books_per_day");

    String name;

    LibraryHeading(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

