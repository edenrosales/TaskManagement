package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddTaskView extends AppCompatActivity{

    //input variables
    String name;
    int start_time;
    int end_time;
    String tag;
    String due_date;
    String description;
    //this is HARDCODED... once notification class is running, we can recieve user inputs for notifications
    Boolean notify_me = false;
    LocalDate due_Date;
    int day, month, year;

    TextView tvSelectDate;


    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;

    //submit button at bottom of page, purpose is to call constructor, add instance to database, and return to to--do list view
    Button submitButton, date_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_view);
        //calling inputs
        nameInput = (EditText) findViewById(R.id.name_input);
        start_timeInput = (EditText) findViewById(R.id.start_time_input);
        end_timeInput = (EditText) findViewById(R.id.end_time_input);
        tagInput = (EditText) findViewById(R.id.tag_input);
        //due_dateInput = (EditText) findViewById(R.id.due_date_input);
        //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        //due_Date = LocalDate.parse(due_dateInput, dateFormat);
        descriptionInput = (EditText) findViewById(R.id.description_input);
        tvSelectDate = findViewById(R.id.due_date_text);
        //etSelectDate = findViewById(R.id.due_date_text);
        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddTaskView.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        tvSelectDate.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        //initializing button
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //we call constructor here
                name = nameInput.getText().toString();
                //right now for testing lets do int 0-24 time... we can convert to float or double once we know display works
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                tag = tagInput.getText().toString();
                Tag t = new Tag(tag);
                due_date = due_dateInput.getText().toString();
                description = descriptionInput.getText().toString();
                //show values to the user
                showText(name);
                showText(String.valueOf(start_time));
                showText(String.valueOf(end_time));
                showText(tag);
                //showText(due_date);
                showText(description);

                //call constructor
                Task new_task = new Task(name, description, t, start_time, end_time, day, month,year ,false);

                //insert into database taskList call goes here


                //call to return to main Activity (To--DoList View)
                openMainView();
            }
        });

    }

    public void openMainView(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showText(String text){
        Toast.makeText(AddTaskView.this, text, Toast.LENGTH_SHORT).show();
    }


}