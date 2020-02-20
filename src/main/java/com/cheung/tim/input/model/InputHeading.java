package com.cheung.tim.input.model;

import com.cheung.tim.Type;

public enum InputHeading implements Type {
    rows("rows"),
    columns("columns"),
    vehicles("vehicles"),
    rides("rides"),
    bonus("bonus"),
    steps("steps");

    String name;

    InputHeading(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

