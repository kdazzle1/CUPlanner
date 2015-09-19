package com.kdazzle.myapplication.Adapters;

/**
 * Created by Eric on 9/19/2015.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import java.util.List;

/**
 * Created by Eric on 8/29/2015.
 */
public class ClassAdapter extends BaseAdapter {

//    private ArrayList<String> mItems = new ArrayList<String>();
    private ArrayList<String> items = new ArrayList<>();

    private Context mContext;

    public ClassAdapter(Context context){
        mContext = context;
    }

    public void add(String item){

        items.add(item);
        notifyDataSetChanged();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setList(ArrayList<String> list){
        items = list;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = (String) getItem(position);
        View classView;
        if(convertView == null){
            LayoutInflater layoutInflater = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            classView = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);

        }
        else{
            classView = convertView;
        }

        TextView text = (TextView) classView.findViewById(android.R.id.text1);
        text.setText(item);


        return classView;
    }


}
