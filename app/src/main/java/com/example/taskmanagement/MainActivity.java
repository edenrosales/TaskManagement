package com.example.taskmanagement;
//import com.example.taskmanagementTaskListTest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    ListView listview;
    public static TaskList taskList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);

        //
        ArrayList<String> names = new ArrayList<>();


        for(int i = 0; i < taskList.Tasks.size(); i++) {
            names.add(taskList.Tasks.get(i).getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,names);
        listview.setAdapter(arrayAdapter);


        //
        //this needs to be changed to to--do list tasks linked list. Just want to make sure tasks is showing up properly first
        //TaskBoxAdapter taskBoxAdapter = new TaskBoxAdapter(this, R.layout.each_task, taskList.Tasks);
        //listview.setAdapter(taskBoxAdapter);
        button = (FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTaskView();
            }
        });
    }
    int current_date;
    public void openAddTaskView(){
        Intent intent = new Intent(this, AddTaskView.class);
        startActivity(intent);
    }
}

