package com.example.labskeletest;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements Favorites.OnFragmentInteractionListener, Map.OnFragmentInteractionListener,List.OnFragmentInteractionListener {
//Connection object
//    private ExpandableListView listView;
//    private ExpandableListAdapter listAdapter;
//    private ArrayList<String> listBuildingHeader;
//    private HashMap<String, java.util.List<String>> listHashMap;

    static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNOQUE_ID";
    DBConfiguration dbc = new DBConfiguration();
    static DBAccess dba = new DBAccess();
    public static ArrayList<Lab> listOfLabs = new ArrayList<Lab>();
    public static  ArrayList<String> listOfFavorites = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();


        if(uniqueID == null){
            SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID,null);

            if(uniqueID == null){
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();

                dba.saveUser(uniqueID);

            }
        }
        System.out.println("Unique ID is: " + uniqueID);
        populateLabList("CEIT");






        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
         tabLayout.addTab(tabLayout.newTab());
         tabLayout.addTab(tabLayout.newTab());
         tabLayout.addTab(tabLayout.newTab());
         tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
       // tabLayout.setupWithViewPager(viewpager, true);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        listView = (ExpandableListView) findViewById(R.id.listView);
//        initData();
//        listAdapter = new ExpandableListAdapter(this, listBuildingHeader, listHashMap);
//        listView.setAdapter(listAdapter);
        //setContentView(R.layout.activity_main);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    public void initData(){
//
//        listHashMap = new HashMap<String, java.util.List<String>>();
//        listBuildingHeader = new ArrayList<>();
//
//        listBuildingHeader.add("IT Building");
//        ArrayList<String> listOfLabsIT = populateLabList(listBuildingHeader.get(0));
//
//        listHashMap.put(listBuildingHeader.get(0),listOfLabsIT);
//
//        listBuildingHeader.add("COBA Building");
//        ArrayList<String> listOfLabsCOBA = populateLabList(listBuildingHeader.get(1));
//
//        listHashMap.put(listBuildingHeader.get(1),listOfLabsCOBA);
//    }
//
//    public ArrayList<String> populateLabList(String building){
//
//        ArrayList<String> listOfLabs = new ArrayList<String>();
//
//        if(building == "IT Building") {
//            listOfLabs.add("1201");
//            listOfLabs.add("1202");
//            listOfLabs.add("1203");
//            listOfLabs.add("1204");
//            listOfLabs.add("1303");
//            listOfLabs.add("2204");
//            listOfLabs.add("2208");
//            listOfLabs.add("2210");
//            listOfLabs.add("2212");
//            listOfLabs.add("3208");
//            listOfLabs.add("3210");
//            listOfLabs.add("3212");
//            listOfLabs.add("3302");
//            listOfLabs.add("3314");
//        }
//
//        return listOfLabs;
//    }

    public String getUUID(){
        return uniqueID;
    }

    public void refreshFavoritesList(){
        listOfFavorites = populateFavoritesList(uniqueID);
    }
    public ArrayList<String> populateFavoritesList(String UUID){
        ArrayList<String> listOfFavorites = new ArrayList<String>();
        try {
            ResultSet rs = dba.getFavorites(uniqueID);
            while (rs.next()) {
                String lab = rs.getString("LabID");
                lab = lab.substring(lab.length() - 4);

                listOfFavorites.add(lab);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return listOfFavorites;
    }
    public static void  populateLabList(String building){


        dba = new DBAccess();

        try {
            ResultSet rs = dba.getLabs(building);

            while (rs.next()) {
                String lab = rs.getString("LabID");
                lab = lab.substring(lab.length() - 4);

                listOfLabs.add(new Lab(lab));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

       // return listOfLabs;
    }

}
