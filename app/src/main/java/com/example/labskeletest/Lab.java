package com.example.labskeletest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lab {

    private String room;
    private String schedule;
    ArrayList <String> softwareList = new <String> ArrayList();
    private int inUseComputers;
    private int totalComputers;

    Lab(String labRoom){
        room = labRoom;

        try {
            getItemStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getItemStatus() throws SQLException {

        DBAccess dba = new DBAccess();
        ResultSet occupancy = dba.getOccupancy(room);

        occupancy.next();
        inUseComputers = occupancy.getInt("InUse");

        occupancy = dba.getTotalComputers(room);

        occupancy.next();
        totalComputers = occupancy.getInt("Total");
    }

    public String getRoom() {
        return room;
    }

    public String getPercentage() {

        String percentage = inUseComputers + "/" + totalComputers;
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
