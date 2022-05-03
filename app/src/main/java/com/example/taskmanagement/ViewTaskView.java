package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTaskView extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    //Task sel_task = new Task("","", new Tag(""), 0, 0, 0, 0, 0, false);
    Task sel_task;
    String selected_task_name, selected_task_description, selected_task_start_time, selected_task_end_time, selected_task_due_date, selected_task_tag;
    Button editButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_view);
        //set the task to be view to the task the user selected in the main activity
        //sel_task = MainActivity.selected_task;






        //edit button has no functionality at the moment
        //still need a design plan to go off from to edit parameters of tasks
        editButton = findViewById(R.id.editTaskButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskView();
            }
        });
    }
    //method to open the edit task view
    public void editTaskView(){
        Intent intent = new Intent(this, ViewTaskView.class);
        startActivity(intent);
    }

}