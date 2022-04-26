package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskView extends AppCompatActivity {

    //input variables
    String name;
    int start_time;
    int end_time;
    String tag;
    String due_date;
    String description;

    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput;

    //submit button at bottom of page, purpose is to call constructor, add instance to database, and return to todo-list view
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_view);
        //calling inputs
        nameInput = (EditText) findViewById(R.id.name_input);
        start_timeInput = (EditText) findViewById(R.id.start_time_input);
        end_timeInput = (EditText) findViewById(R.id.end_time_input);
        tagInput = (EditText) findViewById(R.id.tag_input);
        due_dateInput = (EditText) findViewById(R.id.due_date_input);
        descriptionInput = (EditText) findViewById(R.id.description_input);
        //initializing button
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we call constructor here
                name = nameInput.getText().toString();
                //right now for testing lets do int 0-24 time... we can convert to float or double once we know display works
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                tag = tagInput.getText().toString();
                due_date = due_dateInput.getText().toString();
                description = descriptionInput.getText().toString();

                //show values to the user
                showText(name);
                showText(String.valueOf(start_time));
                showText(String.valueOf(end_time));
                showText(tag);
                showText(due_date);
                showText(description);

            }
        });
    }
    private void showText(String text){
        Toast.makeText(AddTaskView.this, text, Toast.LENGTH_SHORT).show();
    }


}