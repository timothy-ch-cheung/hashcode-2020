package com.cheung.tim.output.model;

import java.util.ArrayList;
import java.util.List;

public class Taxi {

    private Integer id;
    private List<Integer> rides;

    public Taxi(int id){
        this.id = id;
        this.rides = new ArrayList<>();
    }

    public void addRide(Integer ride){
        rides.add(ride);
    }

    public String getRides(){
        StringBuilder rides = new StringBuilder();
        for (Integer i :this.rides){
            rides.append(i);
            rides.append(" ");
        }
        if(this.rides.size() > 0){
            rides.setLength(rides.length()-1);
        }
        return rides.toString();
    }

    public int getNumRides(){
        return rides.size();
    }

    public Integer getId(){
        return this.id;
    }
}
