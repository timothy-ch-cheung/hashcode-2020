package com.cheung.tim.input.model;

import com.cheung.tim.Type;

public enum Rides implements Type {
    startX("startX"),
    startY("startY"),
    endX("endX"),
    endY("endY"),
    latestFinish("latestFinish");

    Rides(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String getName() {
        return this.name;
    }
}
