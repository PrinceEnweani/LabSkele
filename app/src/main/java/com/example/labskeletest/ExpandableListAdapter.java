package com.example.labskeletest;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List <String> listDataHeader;
    private HashMap<String,List<Lab>> listHashMap;

    DBConfiguration dbc = new DBConfiguration();
    DBAccess db = new DBAccess();

    public ExpandableListAdapter(Context content, List<String> listDataHeader, HashMap<String, List<Lab>> listHashMap) {
        this.context = content;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_textview, null);
        }

        TextView listLabel = (TextView) view.findViewById(R.id.lvHeaders);
        listLabel.setTypeface(null , Typeface.BOLD);
        listLabel.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        final Lab child = (Lab) getChild(groupPosition, childPosition);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem, null);
        }

        TextView roomLabel = (TextView) view.findViewById(R.id.tvLabNumber);
        roomLabel.setText(child.getRoom());

        TextView percentLabel = (TextView) view.findViewById(R.id.tvLabPercent);
        percentLabel.setText(child.getPercentage());

        ToggleButton favoriteBtn = (ToggleButton) view.findViewById(R.id.toggleButton);

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Favorite has been clicked. Lab room:" + child.getRoom());

                String UUID = MainActivity.uniqueID;
                String room = child.getRoom();
                db = new DBAccess();
                db.saveFavorite(UUID, room);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
