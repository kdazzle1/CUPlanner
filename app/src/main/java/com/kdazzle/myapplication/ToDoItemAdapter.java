package com.kdazzle.myapplication;

/**
 * Created by Eric on 9/19/2015.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ToDoItemAdapter extends BaseAdapter {

    private ArrayList<ToDoItem> mItems = new ArrayList<ToDoItem>();
    private Context mContext;

    //State of the row that needs to show separator
    private static final int SECTIONED_STATE = 1;
    //State of the row that does not need to show separator
    private static final int REGULAR_STATE = 2;
    //Keeps track of row states
    private int[] rowStates;

    //Separator strings
    private static final String[] dividers = {"Today", "Tomorrow", "Later this Week", "Later this Month", "Later"};

    public ToDoItemAdapter(Context context){
        mContext = context;
    }

    public void setList(ArrayList<ToDoItem> list){
        mItems = list;
        notifyDataSetChanged();
    }

    public ArrayList<ToDoItem> getList(){
        return mItems;
    }


    public void add(ToDoItem item) {

        mItems.add(item);
        Collections.sort(mItems);
        notifyDataSetChanged();

    }

    public ArrayList<ToDoItem> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<ToDoItem> mItems) {
        this.mItems = mItems;
    }


    public void clear() {
        mItems.clear();
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoItem item = (ToDoItem) getItem(position);
        View toDoItemView;

        if(convertView == null){
            LayoutInflater layoutInflater = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            toDoItemView = layoutInflater.inflate(R.layout.to_do_item_view,parent,false);

        }
        else{
            toDoItemView = convertView;
        }


        TextView title = (TextView) toDoItemView.findViewById(R.id.title);
        TextView dateAndTime = (TextView) toDoItemView.findViewById(R.id.dateAndTime);

        title.setText(item.getGroup());
        dateAndTime.setText(item.getTime() + " on " + item.getDate());

        //This should be decided if the textview above is within the time range (today, tomorrow, later this week, etc.)
        boolean showSeparator = true;

        TextView separatorView = (TextView) toDoItemView.findViewById(R.id.separator);

        if (showSeparator) {
            if (getProximity(item) == "TODAY") {
                separatorView.setText(dividers[0]);
                separatorView.setVisibility(View.VISIBLE);
            }
            else if (getProximity(item) == "TOMORROW") {
                separatorView.setText(dividers[1]);
                separatorView.setVisibility(View.VISIBLE);
            }
            else if (getProximity(item) == "LATER THIS WEEK") {
                separatorView.setText(dividers[2]);
                separatorView.setVisibility(View.VISIBLE);
            }
            else if (getProximity(item) == "LATER THIS MONTH") {
                separatorView.setText(dividers[3]);
                separatorView.setVisibility(View.VISIBLE);
            }
            else if (getProximity(item) == "LATER") {
                separatorView.setText(dividers[4]);
                separatorView.setVisibility(View.VISIBLE);
            }
        }
        else {
            toDoItemView.findViewById(R.id.separator).setVisibility(View.GONE);
        }



        return toDoItemView;
    }

    public String getProximity(ToDoItem item){
        String[] mdy = item.getDate().split("/");

        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);

        Calendar thatDay = Calendar.getInstance();
        int tmonth = Integer.parseInt(mdy[0]);
        int tday = Integer.parseInt(mdy[1]);
        int tyear = Integer.parseInt(mdy[2]);

        thatDay.set(Calendar.DAY_OF_MONTH, tday);
        thatDay.set(Calendar.MONTH, tmonth - 1);
        thatDay.set(Calendar.YEAR, tyear);

        long targetDay = thatDay.getTimeInMillis();
        long thisDay = today.getTimeInMillis();

        //The number of milliseconds in a day
        long dayMilliseconds = 24 * 60 * 60 * 1000;

        //Millisecond difference between ToDoItem date and today
        long diff = targetDay - thisDay;

        //Milliseconds from this time and 12:00AM of the same day
        long offset = (thisDay % (24 * 60 * 60 * 1000));

        //The exact number of milliseconds at 12:00AM same day
        long midnight = thisDay - offset;

        //The exact number of milliseconds at 12:00AM next day
        long nextDay = midnight + (24 * 60 * 60 * 1000);

        //The exact number of milliseconds at the beginning of the week
        long beginWeek = midnight - ((today.get(Calendar.DAY_OF_WEEK) - 1) * dayMilliseconds);

        //The exact number of milliseconds at the beginning of the month
        long beginMonth = midnight - ((today.get(Calendar.DAY_OF_MONTH) - 1) * dayMilliseconds);

        if (targetDay < nextDay)
            return "TODAY";

        else if (targetDay >= nextDay && targetDay < nextDay + dayMilliseconds)
            return "TOMORROW";

        else if (targetDay >= nextDay + dayMilliseconds &&
                targetDay <= beginWeek + (7 * dayMilliseconds))
            return "LATER THIS WEEK";

        else if (targetDay > beginWeek + (7 * dayMilliseconds) &&
                targetDay <= beginMonth + (today.getActualMaximum(Calendar.DAY_OF_MONTH) * dayMilliseconds))
            return "LATER THIS MONTH";

        else
            return "LATER";
    }
}
