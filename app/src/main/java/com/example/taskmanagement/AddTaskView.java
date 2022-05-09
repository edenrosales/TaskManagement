package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddTaskView extends AppCompatActivity{
    private static final String TAG = "AddTaskView";
    public static String EXTRA_TITLE =
            "empty";
    public static String EXTRA_DESCRIPTION =
            "empty";
    public static String EXTRA_START =
            "empty";
    public static String EXTRA_END =
            "empty";
    //public static String EXTRA_DUE_DATE =
    //        "empty";
    public static String EXTRA_DAY =
            "empty";
    public static String EXTRA_MONTH =
            "empty";
    public static String EXTRA_YEAR =
            "empty";
    public static String EXTRA_TAG =
            "empty";

    private EditText editTextName;
    private EditText editTextDescription;
    private NumberPicker numberPickerStart;
    private NumberPicker numberPickerEnd;
    TaskViewModel taskViewModel;

    //input variables
    //this is HARDCODED... once notification class is running, we can recieve user inputs for notifications
    int day, month, year;

    TextView tvSelectDate;

    //EditText nameInput, start_timeInput, end_timeInput, tagInput , due_dateInput, descriptionInput, etSelectDate;

    //submit button at bottom of page, purpose is to call constructor, add instance to database, and return to to--do list view
    Button submitButton, date_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_view_act);
        editTextName = findViewById(R.id.name_input2);
        editTextDescription = findViewById(R.id.description_input2);
        numberPickerStart = findViewById(R.id.number_picker_start);numberPickerStart.setMinValue(1); numberPickerStart.setMaxValue(12);
        numberPickerEnd = findViewById(R.id.number_picker_end); numberPickerEnd.setMinValue(1);  numberPickerEnd.setMaxValue(12);
        tvSelectDate = findViewById(R.id.due_date_text2);
        //etSelectDate = findViewById(R.id.due_date_text);
        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddTaskView.this, new DatePickerDialog.OnDateSetListener() {
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


        //declare spinner
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                List<String> names = Tag.getListOfTagNamesLiveData(tags);
                ArrayAdapter<String> aDapter = new ArrayAdapter<String>(AddTaskView.this, android.R.layout.simple_list_item_1, names);
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
                        //do nothing on no item selected
                    }
                });
            }
        });



        //initializing button
        submitButton = (Button) findViewById(R.id.button2);
        submitButton.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                    saveNote();
            }
        });

    }

    private void saveNote(){
        String title = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        int start = numberPickerStart.getValue();
        int end = numberPickerEnd.getValue();
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please Insert a name and description", Toast.LENGTH_SHORT).show();
            return;
        }
        //month = month + 1;
        Intent data = new Intent();
        data.putExtra("EXTRA_NAME", title);
        data.putExtra("EXTRA_DESCRIPTION", description);
        data.putExtra("EXTRA_START", start);
        data.putExtra("EXTRA_END", end);
        //data.putExtra("EXTRA_TAG", (Parcelable) tag); //gave me the exception
        data.putExtra("EXTRA_DAY", day);
        data.putExtra("EXTRA_MONTH", month);
        data.putExtra("EXTRA_YEAR", year);
        //data.putExtra("EXTRA_DUE_DATE",  dt);
        setResult(RESULT_OK, data);
        finish();
    }

}