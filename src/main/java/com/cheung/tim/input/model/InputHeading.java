package com.cheung.tim.input.model;

import com.cheung.tim.Type;

public enum InputHeading implements Type {
    book_num("book_num"),
    library_num("library_num"),
    scanning_time("scanning_time");

    String name;

    InputHeading(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

