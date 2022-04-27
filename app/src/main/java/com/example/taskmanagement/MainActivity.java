package com.example.taskmanagement;
//import com.example.taskmanagementTaskListTest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    ListView listview;
    String name;
    String description;
    public static TaskList taskList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        //call for adapter to show list of task objects in a list
        TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, taskList.Tasks );
        listview.setAdapter(tAdapter);

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

