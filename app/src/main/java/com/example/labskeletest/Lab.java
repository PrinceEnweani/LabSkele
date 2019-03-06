package com.example.labskeletest;

public class Lab {

    private String room;
    private String schedule;

    Lab(String room , String schedule){
        this.room = room;
        this.schedule = schedule;
    }

    public String getRoom() {
        return room;
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
