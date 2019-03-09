package com.example.labskeletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabInformation extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListLabInfoAdapater listAdapter;
    private ArrayList<String> listBuildingHeader;
    private HashMap<String, List<String>> listHashMap;
    private String passedLab;
    private int inUseComputers;
    private int totalComputers;
    //DB TEST
    DBConfiguration dbc = new DBConfiguration();
    DBAccess db = new DBAccess();
    ResultSet computersRS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_information);
        Intent i = getIntent();
        passedLab = i.getStringExtra("labClicked");
        listView = findViewById(R.id.listViewLabInfo);

        try {
            loadLabOccupancy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initData();
        listAdapter = new ExpandableListLabInfoAdapater(this, listBuildingHeader, listHashMap);
        listView.setAdapter(listAdapter);
        System.out.println("You clicked the lab number" + passedLab);
        try {
            loadLabOccupancy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initData(){

        listHashMap = new HashMap<String, java.util.List<String>>();
        listBuildingHeader = new ArrayList<>();
        listBuildingHeader.add("Lab " + passedLab);
       // ArrayList<String> listOfLabsIT = populateLabList(listBuildingHeader.get(0));

        //listHashMap.put(listBuildingHeader.get(0),listOfLabsIT);

        listBuildingHeader.add("Occupancy");
        ArrayList<String> listOfLabsCOBA = populateLabList(listBuildingHeader.get(1));
        listHashMap.put(listBuildingHeader.get(1),listOfLabsCOBA);

        listBuildingHeader.add("Printer:");

        listBuildingHeader.add("Lab Hours:");
        ArrayList<String> listOfHours = populateHours();
        listHashMap.put(listBuildingHeader.get(3), listOfHours);

        listBuildingHeader.add("Software Available:");
        ArrayList<String> listOfSoftware = populateSoftware();
        listHashMap.put(listBuildingHeader.get(4), listOfSoftware);
    }
    public ArrayList<String> populateSoftware( ){
        ArrayList<String> listOfSoftware = new ArrayList<String>();
        listOfSoftware.add("Eclipse");
        listOfSoftware.add("Notepad");
        listOfSoftware.add("Chrome");
        return listOfSoftware;
    }
    public ArrayList<String> populateHours( ){
        ArrayList<String> listOfHours = new ArrayList<String>();
        listOfHours.add("Monday: x-x");
        listOfHours.add("Tuesday: x-x");
        listOfHours.add("Wednesday: x-x");
        listOfHours.add("Thursday: x-x");
        listOfHours.add("Friday: x-x");
        listOfHours.add("Saturday: x-x");
        listOfHours.add("Sunday: x-x");
        return listOfHours;
    }
    public ArrayList<String> populateLabList(String building){
        ArrayList<String> listOfLabs = new ArrayList<String>();
        String a = Integer.toString(inUseComputers);
        String b = Integer.toString(totalComputers);
        String c = "In Use: " + a + "/" + b;
        listOfLabs.add(c);
        return listOfLabs;
    }
    public void loadLabOccupancy() throws SQLException {
        DBAccess dba = new DBAccess();
        ResultSet occupancy = dba.getOccupancy(passedLab);

        occupancy.next();
        inUseComputers = occupancy.getInt("InUse");

        occupancy = dba.getTotalComputers(passedLab);

        occupancy.next();
        totalComputers = occupancy.getInt("Total");


        System.out.println("Computers In Use: " + inUseComputers + "/" + totalComputers );
    }
}
