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
    String selected_task_name, selected_task_description, selected_task_start_time, selected_task_end_time, selected_task_due_date, selected_task_tag;
    TextView name, description, start_time, end_time, due_date, tag;
    Button editButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_view);
        name = findViewById(R.id.text_name_view_task);
        description = findViewById(R.id.text_description_for_view_task);
        start_time = findViewById(R.id.text_start_time_for_view_task);
        end_time = findViewById(R.id.text_end_time_for_view_task);
        due_date = findViewById(R.id.text_due_date_for_view_task);
        tag = findViewById(R.id.text_tag_for_view_task);
        //set the task to be view to the task the user selected in the main activity
        //name.setText(MainActivity.selected_task.getName().toString());
        name.setText(MainActivity.selected_task.name.toString());
        description.setText(MainActivity.selected_task.description.toString());
        start_time.setText(Integer.valueOf(MainActivity.selected_task.start_time).toString());
        end_time.setText(Integer.valueOf(MainActivity.selected_task.end_time).toString());
        //due date we wil leave for now
        tag.setText(MainActivity.selected_task.associated_tag.getName().toString());




        //edit button has no functionality at the moment
        //still need a design plan to go off from to edit parameters of tasks
        editButton = findViewById(R.id.editTaskButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we call the edit task view function to jump to edit or delete the task
                editTaskView();
            }
        });
    }
    //method to open the edit task view
    public void editTaskView(){
        Intent intent = new Intent(ViewTaskView.this, EditingTask.class);
        startActivity(intent);
    }

}