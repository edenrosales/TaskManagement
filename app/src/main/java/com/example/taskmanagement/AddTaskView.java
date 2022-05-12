package com.example.taskmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import android.widget.SpinnerAdapter;
import android.widget.Switch;

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
//<<<<<<< HEAD
    TaskViewModel taskViewModel;
    private boolean notifIsChecked;
//=======
//    private boolean notifIsChecked;

//>>>>>>> origin/Notifications

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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(AddTaskView.this,"channel_id");

        /**This code is for Notifications**/
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("channel_id","channel name",NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channel description");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        /**************************************************************************/
        //declare spinner for tag
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);

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
                    public void onDateSet(DatePicker view, int selected_year, int selected_month, int selected_day) {
                        selected_month = selected_month + 1;
                        //was day instead of dayOfMonth, this might work
                        day = selected_day;////
                        month= selected_month;
                        year = selected_year;
                        String date = selected_day+"/"+selected_month+"/"+selected_year;
                        tvSelectDate.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });


        //declare spinner
        //Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
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


        Switch aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.d("XXXXXXX","IT WORKS");
                    //When task added, Notification pops up....
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setContentTitle(editTextName.getText().toString()); //header
                    builder.setContentText(editTextDescription.getText().toString()); //descrip
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH); //heads-up
                    builder.setAutoCancel(true);
                    notifIsChecked = true;
                    //builder.setSmallIcon(R.mipmap.ic_launcher); //incase UI goes ape shit...



                }
                //No Notification... this is default
                else{
                    Log.d("ZZZZZZZZ","IT IS OFF");
                    notifIsChecked = false;
                }
            }
        });


        //initializing button
        submitButton = (Button) findViewById(R.id.button2);
        submitButton.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //Tag current_tag = new Tag("",0);
                //we call constructor here
                /*name = nameInput.getText().toString();
                //right now for testing lets do int 0-24 time... we can convert to float or double once we know display works
                start_time = Integer.valueOf(start_timeInput.getText().toString());
                end_time = Integer.valueOf(end_timeInput.getText().toString());
                tag = mySpinner.toString();
                for(int i = 0; i < MainActivity.taskList.getTags().size() ; i++){
                    if(MainActivity.taskList.Tags.get(i).name.equals(tag)){
                      //set current tag to constructor
                        current_tag = MainActivity.taskList.Tags.get(i);
                        //need to set tag to new created task
                        /***************************************/
                        //
                //    }
                //}
                //int min = 50;
                //int max = 100;
                //Generate random int value from 50 to 100
                //int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
                //for not it will be a random integer when adding tasks
                // I WILL NEED TO IMPLEMENT A DRAG DROP MENU FOR CHOOSING AN EXISTING TAG
                //Tag new_tag = new Tag(tag, random_int);
                //due_date = due_dateInput.getText().toString();
                //description = descriptionInput.getText().toString();
                //show values to the user
                //showText(name);
                //showText(String.valueOf(start_time));
                //showText(String.valueOf(end_time));
                //showText(tag);
                //showText(due_date);
                //showText(description);
                //call constructor
                //if tag == null set tag to "all"/default tag
                //new_task = new Task(name, description, current_tag, start_time, end_time, day, month + 1,year ,false);
                //insert into database taskList call goes here
                //MainActivity.taskList.Tasks.add(new_task);
                //MainActivity.taskList.TodoListTasks.add(new_task);
                //System.out.println("TASKLIST SIZE: " + MainActivity.taskList.Tasks.size());
                //call to return to main Activity (To--DoList View)
                //is_Task_added = true;
                //openMainView();
                //*/


                //if Toggle is checked, show Notif when submit task
                if(notifIsChecked) {
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AddTaskView.this);
                    managerCompat.notify(1, builder.build());
                }


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