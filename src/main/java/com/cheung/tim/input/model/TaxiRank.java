package com.cheung.tim.input.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiRank {
    public Map<String, Integer> header;
    public List<Map<String, Integer>> rides;

    public TaxiRank(Map<String, Integer> header, List<Map<String, Integer>> rides) {
        this.header = header;
        this.rides = rides;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("TaxiRank{" +
                "header=" + header.toString() +
                ",\n\n rides =\n");

        for (Map<String, Integer> ride : rides) {
            out.append(ride + "\n");
        }

        out.append('}');
        return out.toString();
    }

    public Map<String, Integer> getHeader(){
        return this.header;
    }

    public List<Map<String, Integer>> getRides(){
        return this.rides;
    }
}
