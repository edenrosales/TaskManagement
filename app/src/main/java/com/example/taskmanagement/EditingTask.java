package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;

public class EditingTask extends AppCompatActivity {
    String name, due_date, tag, description;
    int start_time, end_time;
    LocalDate due_Date;
    int day, month, year;
    boolean is_Task_edited = false;
    Tag current_tag = new Tag("",0);
    TextView tvSelectDate;
    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;
    Button submit, delete;
    Spinner mySpinner;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        /************************************************************************/
        //setting text for each item before editing
        nameInput.setText(MainActivity.selected_task.getName().toString());
        descriptionInput.setText(MainActivity.selected_task.getDescription().toString());
        start_timeInput.setText(Integer.valueOf(MainActivity.selected_task.getStart()).toString());
        end_timeInput.setText(Integer.valueOf(MainActivity.selected_task.getEnd()).toString());

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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.taskList.Tasks.remove(MainActivity.selected_task);
                openMainView();

                //delete button code  for selected_task goes here
                //MainActivity.taskList.TodoListTasks.remove();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                name = nameInput.getText().toString();
                description = descriptionInput.getText().toString();
                tag = mySpinner.toString();
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                showText(name);
                showText(String.valueOf(start_time));
                showText(String.valueOf(end_time));
                showText(description);
                //edit setter code for selected_task goes here
                if(!name.equals(MainActivity.selected_task.getName())){
                    //set name equal to selected_task name
                    MainActivity.selected_task.setName(name);
                }
                if(!description.equals(MainActivity.selected_task.getDescription())){
                    MainActivity.selected_task.setDescription(description);
                }
                if((Integer) start_time != MainActivity.selected_task.getStart()){
                    MainActivity.selected_task.setStart(start_time);
                }
                if((Integer) end_time != MainActivity.selected_task.getEnd()){
                    MainActivity.selected_task.setEnd(end_time);
                }
                //for tag


                //setter for dueDate
                //int new_day = MainActivity.selected_task.getDue().getDayOfMonth();
                //int new_month = MainActivity.selected_task.getDue().getMonthValue();
                //int new_year = MainActivity.selected_task.getDue().getYear();
                //String date = new_day + "/" + new_month + "/" + new_year;
                //if(date.equals(day+"/"+month+"/"+year)){
                //    MainActivity.selected_task.setDue(day, month, year);
                //}

                openMainView();
            }
        });



    }

    public void openMainView(){
        Intent intent = new Intent(EditingTask.this, MainActivity.class);
        startActivity(intent);
    }

    private void showText(String text){
        Toast.makeText(EditingTask.this, text, Toast.LENGTH_SHORT).show();
    }



}