package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;


public class AddTaskView extends AppCompatActivity{
    private static final String TAG = "MainActivity";
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
    Task new_task;
    boolean is_Task_added = false;

    TextView tvSelectDate;

    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;

    //submit button at bottom of page, purpose is to call constructor, add instance to database, and return to to--do list view
    Button submitButton, date_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_view_act);
        //calling inputs
        nameInput = (EditText) findViewById(R.id.name_input2);
        start_timeInput = (EditText) findViewById(R.id.start_time_input2);
        end_timeInput = (EditText) findViewById(R.id.end_time_input2);

        /**************************************************************************/
        //declare spinner for tag
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
        LinkedList<Tag> c_tags = MainActivity.taskList.getTags();
        LinkedList<String> tagNames = Tag.getListOfTagNames(c_tags);
        ArrayAdapter<String> aDapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tagNames);
        aDapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(aDapter);
        /**************************************************************************/
        //due_dateInput = (EditText) findViewById(R.id.due_date_input);
        //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        //due_Date = LocalDate.parse(due_dateInput, dateFormat);
        descriptionInput = (EditText) findViewById(R.id.description_input2);
        tvSelectDate = findViewById(R.id.due_date_text2);
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
        submitButton = (Button) findViewById(R.id.button2);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Tag current_tag = new Tag("",0);
                //we call constructor here
                name = nameInput.getText().toString();
                //right now for testing lets do int 0-24 time... we can convert to float or double once we know display works
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                tag = mySpinner.toString();
                for(int i = 0; i < MainActivity.taskList.getTags().size() ; i++){
                    if(MainActivity.taskList.Tags.get(i).name.equals(tag)){
                      //set current tag to constructor
                        current_tag = MainActivity.taskList.Tags.get(i);
                    }
                }
                int min = 50;
                int max = 100;
                //Generate random int value from 50 to 100
                int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
                //for not it will be a random integer when adding tasks
                // I WILL NEED TO IMPLEMENT A DRAG DROP MENU FOR CHOOSING AN EXISTING TAG
                //Tag new_tag = new Tag(tag, random_int);
                //due_date = due_dateInput.getText().toString();
                description = descriptionInput.getText().toString();
                //show values to the user
                showText(name);
                showText(String.valueOf(start_time));
                showText(String.valueOf(end_time));
                //showText(tag);
                //showText(due_date);
                showText(description);
                //call constructor
                //if tag == null set tag to "all"/default tag
                new_task = new Task(name, description, current_tag, start_time, end_time, day, month + 1,year ,false);
                //insert into database taskList call goes here
                MainActivity.taskList.Tasks.add(new_task);
                MainActivity.taskList.TodoListTasks.add(new_task);
                System.out.println("TASKLIST SIZE: " + MainActivity.taskList.Tasks.size());
                //call to return to main Activity (To--DoList View)
                is_Task_added = true;
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