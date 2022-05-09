package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class EditingTask extends AppCompatActivity {
    private static final String TAG = "EditTaskView";
    public static String EXTRA_TASK =
            "empty";
    public static String EXTRA_ID =
            "empty";
    public static String EXTRA_TAG =
            "empty";

    String name, due_date, tag, description;
    int start_time, end_time;
    LocalDate due_Date;
    int day, month, year;
    boolean is_Task_edited = false;
    Tag current_tag = new Tag("", R.color.teal_700);
    TextView tvSelectDate;
    EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;
    Button submit, delete,toggleCompleted;
    Spinner mySpinner;
    TaskViewModel taskViewModel;
    String selected_date;

    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_task);
        Intent intent = getIntent();
        toggleCompleted = findViewById(R.id.completeTask);
        submit = findViewById(R.id.submit_edit);
        delete = findViewById(R.id.deleteTaskButton);
        //text input objects
        nameInput = (EditText) findViewById(R.id.name_edit_task);
        start_timeInput = (EditText) findViewById(R.id.text_start_time_for_edit_task);
        end_timeInput = (EditText) findViewById(R.id.text_end_time_for_edit_task);
        descriptionInput = (EditText) findViewById(R.id.text_description_for_edit_task);
        due_dateInput = (EditText) findViewById(R.id.text_due_date_for_edit_task);
        nameInput.setText(MainActivity.selected_task.getName().toString());
        descriptionInput.setText(MainActivity.selected_task.getDescription().toString());
        start_timeInput.setText(Integer.valueOf(MainActivity.selected_task.getStart()).toString());
        end_timeInput.setText(Integer.valueOf(MainActivity.selected_task.getEnd()).toString());
        System.out.println(MainActivity.selected_task.getDateToString());
        due_dateInput.setText(MainActivity.selected_task.getDateToString());
        if(MainActivity.selected_task.getCompleted()){
            toggleCompleted.setText("Uncomplete");
            toggleCompleted.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        else{
            toggleCompleted.setText("Complete");
            toggleCompleted.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        Spinner mySpinner = (Spinner) findViewById(R.id.text_tag_for_edit_task);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                List<String> names = Tag.getListOfTagNamesLiveData(tags);
                ArrayAdapter<String> aDapter = new ArrayAdapter<String>(EditingTask.this, android.R.layout.simple_list_item_1, names);
                aDapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
                mySpinner.setAdapter(aDapter);
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        MainActivity.selected_tag = tags.get(position);
                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
            }
        });
        List<Tag> c_tags = MainActivity.taskList.getTags();
        List<String> tagNames = Tag.getListOfTagNames(c_tags);
        ArrayAdapter<String> aDapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tagNames);
        aDapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(aDapter);
        tvSelectDate = findViewById(R.id.text_due_date_for_edit_task);
        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(java.util.Calendar.YEAR);
        month = c.get(java.util.Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(EditingTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selected_year, int selected_month, int selected_day) {
                        String date = (selected_month+1) +"/"+selected_day+"/"+selected_year;
                        tvSelectDate.setText(date);
                        day = selected_day;////
                        year = selected_year;
                    }
                },year, c.get(Calendar.MONTH), day);
                dialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                name = nameInput.getText().toString();
                description = descriptionInput.getText().toString();
                tag = mySpinner.toString();
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                String date = MainActivity.selected_task.getDateToString();
                //showText(name);
                //showText(String.valueOf(start_time));
                //showText(String.valueOf(end_time));
                //showText(description);
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
                if(!MainActivity.selected_task.getTag().getName().equals(MainActivity.selected_tag.getName())){
                    MainActivity.selected_task.setTag(MainActivity.selected_tag);
                }
                //this might be the problem
//                if(!selected_date.equals(date)){
//                    MainActivity.selected_task.setDue(year, month, day);
//                }
                Intent data = new Intent();
                //set result code = -2
                setResult(-2, data);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete button code  for selected_task goes here
                //MainActivity.taskList.TodoListTasks.remove();
                deleteTask();
            }
        });
        toggleCompleted.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(MainActivity.selected_task.getCompleted()){
//                    System.out.println("No");
                    MainActivity.selected_task.completed = false;
                    toggleCompleted.setText("Uncomplete");

                    toggleCompleted.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                else if(!MainActivity.selected_task.getCompleted()){
//                    System.out.println("Yes");
                    MainActivity.selected_task.completed = true;
                    toggleCompleted.setText("Complete");
                    toggleCompleted.setBackgroundColor(Color.parseColor("#FF0000"));

                }
            }
        });
    }
    private void deleteTask(){
        Intent data = new Intent();
        setResult(-1, data);
        finish();
    }
    public void openMainView(){
        Intent intent = new Intent(EditingTask.this, MainActivity.class);
        startActivity(intent);
    }
}