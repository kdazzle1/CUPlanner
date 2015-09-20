package com.kdazzle.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kdazzle.myapplication.Adapters.ToDoItemAdapter;

import java.util.ArrayList;

/**
 * Created by Eric on 9/19/2015.
 */
public class ShowList extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_list);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        ArrayList<ToDoItem> list = i.getParcelableArrayListExtra("list");

        ListView l = (ListView) findViewById(R.id.activity_show_list);

        setTitle(name);

        ToDoItemAdapter adapter = new ToDoItemAdapter(this);
        adapter.setList(list);
        l.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                finish();
        }
        return true;
    }
}
