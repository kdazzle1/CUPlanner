package com.kdazzle.myapplication;

/**
 * Created by Eric on 9/19/2015.
 */

import android.content.Context;
import android.graphics.Color;
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

/**
 * Created by Eric on 8/29/2015.
 */
public class ToDoItemAdapter extends BaseAdapter {

    private ArrayList<ToDoItem> mItems = new ArrayList<ToDoItem>();
    private Context mContext;

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



        return toDoItemView;
    }
}
