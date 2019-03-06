package com.example.labskeletest;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Favorites.OnFragmentInteractionListener, Map.OnFragmentInteractionListener,List.OnFragmentInteractionListener {
//Connection object
//    private ExpandableListView listView;
//    private ExpandableListAdapter listAdapter;
//    private ArrayList<String> listBuildingHeader;
//    private HashMap<String, java.util.List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
