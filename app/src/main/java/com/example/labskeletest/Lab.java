package com.example.labskeletest;

import java.util.ArrayList;

public class Lab {

    private String room;
    private String schedule;
    ArrayList <String> softwareList = new <String> ArrayList();

    Lab(String room){
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public String getPercentage() {

        int inUse = 0;
        int total = 0;

        String percentage = inUse + "/" + total;
        return percentage;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }



}
