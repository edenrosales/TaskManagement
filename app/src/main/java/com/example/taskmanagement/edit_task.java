package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class edit_task extends AppCompatActivity {
    String name, start, end, due, tag, description;
    EditText editName, editStart, editEnd, editDue, editTag, editDescription;
    Button submit, delete;
    Spinner mySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        //initalizing buttons
        submit = findViewById(R.id.submit_edit);
        delete = findViewById(R.id.deleteTaskButton);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete button code goes here
                MainActivity.taskList.TodoListTasks.remove();
            }
        });


    }
}