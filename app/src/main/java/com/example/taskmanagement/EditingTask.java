package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;

public class EditingTask extends AppCompatActivity {
    String name, start_time, end_time, due_date, tag, description;
    LocalDate due_Date;
    int day, month, year;
    boolean is_Task_edited = false;
    Tag current_tag = new Tag("",0);
    TextView tvSelectDate;
    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;
    Button submit, delete;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_task);

        //initalizing buttons
        submit = findViewById(R.id.submit_edit);
        delete = findViewById(R.id.deleteTaskButton);

        //text input objects
        nameInput = (EditText) findViewById(R.id.name_edit_task);
        start_timeInput = (EditText) findViewById(R.id.text_start_time_for_edit_task);
        end_timeInput = (EditText) findViewById(R.id.text_end_time_for_edit_task);
        descriptionInput = (EditText) findViewById(R.id.text_description_for_edit_task);
        /**************************************************************************/
        //declare spinner for tag
        Spinner mySpinner = (Spinner) findViewById(R.id.text_tag_for_edit_task);
        LinkedList<Tag> c_tags = MainActivity.taskList.getTags();
        LinkedList<String> tagNames = Tag.getListOfTagNames(c_tags);
        ArrayAdapter<String> aDapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tagNames);
        aDapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(aDapter);
        /**************************************************************************/
        //
        tvSelectDate = findViewById(R.id.text_due_date_for_edit_task);

        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(java.util.Calendar.YEAR);
        month = c.get(java.util.Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog( EditingTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        tvSelectDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //edit setter code for selected_task goes here
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete button code  for selected_task goes here
                //MainActivity.taskList.TodoListTasks.remove();
            }
        });
    }


}