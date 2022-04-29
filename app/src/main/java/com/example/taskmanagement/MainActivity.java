package com.example.taskmanagement;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    ListView listview;
    String name;
    String description;
    //selected task will be default until a use picks a task
    //@RequiresApi(api = Build.VERSION_CODES.O)
    //public static Task selected_task = new Task("", "", new Tag(""), 0, 0, 0, 0, 0, false);
    public static TaskList taskList = new TaskList();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        //
        //10 tasks will be added here
        Tag t1 = new Tag("School");
        taskList.Tasks.add(new Task("Task 1", "This is the description of Task 1. Must do sub item 1 and 2 of Task 1", t1, 1, 2, 2, 10, 2022, false ));
        taskList.Tasks.add(new Task("Task 2", "This is the description of Task 2. Must do sub item 1 and 2 of Task 2", t1, 3, 4, 3, 10, 2022, false ));
        taskList.Tasks.add(new Task("Task 3", "This is the description of Task 3. Must do sub item 1 and 2 of Task 3", t1, 5, 6, 4, 10, 2022, false ));
        taskList.Tasks.add(new Task("Task 4", "This is the description of Task 4. Must do sub item 1 and 2 of Task 4", t1, 7, 8, 5, 10, 2022, false ));
        taskList.Tasks.add(new Task("Task 5", "This is the description of Task 5. Must do sub item 1 and 2 of Task 5", t1, 9, 10, 6, 10, 2022, false ));








        //
        //call for adapter to show list of task objects in a list
        TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, taskList.Tasks );
        listview.setAdapter(tAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //if a task item is clicked, code will jump here

                //we need to specify for each task however and its data
                //jump to view_task_view
                //selected_task = taskList.Tasks.get(i);
                //viewTaskView();
            }
        });

        button = (FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTaskView();
            }
        });
    }
    int current_date;
    public void openAddTaskView(){
        Intent intent = new Intent(this, AddTaskView.class);
        startActivity(intent);
    }
    public void viewTaskView(){
        Intent intent = new Intent(this, ViewTaskView.class);
        startActivity(intent);
    }
}

