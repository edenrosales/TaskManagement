package com.example.taskmanagement;
import static com.example.taskmanagement.TaskList.filterTasksByDate;
import static com.example.taskmanagement.TaskList.parseTags;
import static com.example.taskmanagement.TaskList.removeDuplicateTags;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private FloatingActionButton tagButton;
    ListView listview;
    String name;
    String description;
    LocalDate today;
    //selected task will be default until a use picks a task
    //@RequiresApi(api = Build.VERSION_CODES.O)
    //public static Task selected_task = new Task("", "", new Tag(""), 0, 0, 0, 0, 0, false);
    public static TaskList taskList = new TaskList();
    public static Tag all = new Tag("All", R.color.teal_700);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        TodoList todo = new TodoList();
        //getting todoListTasks
        todo.list = taskList.getTodoListTasks();
        //get the current date
        today = LocalDate.now();
        //10 tasks will be added here
       //Tag t1 = new Tag("S", R.color.teal_200);
       // Tag t2 = new Tag("W", R.color.white);
       // taskList.Tags.add(t1);
       // taskList.Tags.add(t2);
       // taskList.Tasks.add(new Task("Task 1", "This is the description of Task 1. Must do sub item 1 and 2 of Task 1", t1, 1, 2, 29, 10, 2022, false ));
       // taskList.Tasks.add(new Task("Task 2", "This is the description of Task 2. Must do sub item 1 and 2 of Task 2", t2, 3, 4, 3, 10, 2022, false ));
       // taskList.Tasks.add(new Task("Task 3", "This is the description of Task 3. Must do sub item 1 and 2 of Task 3", t2, 5, 6, 4, 10, 2022, false ));
       // taskList.Tasks.add(new Task("Task 4", "This is the description of Task 4. Must do sub item 1 and 2 of Task 4", t1, 7, 8, 5, 10, 2022, false ));
       // taskList.Tasks.add(new Task("Task 5", "This is the description of Task 5. Must do sub item 1 and 2 of Task 5", t2, 9, 10, 6, 10, 2022, false ));
        //out todoList Tasks will be filtered by date first, the last parameter, 1, is todoList View
        //taskList.TodoListTasks = filterTasksByDate(taskList.Tasks, today, 1);
        //filtering todo list tasks by date
        todo.list = filterTasksByDate(todo.list, today, 1);
        //Need to display current input tags
        TabLayout t = (TabLayout) findViewById(R.id.tag_tab_layout);
            //Find the all relevant tags that are represented all items in todoList...Only need one tag for display, since multiple tasks can share a tag
        //LinkedList<Tag> current_tags = parseTags(taskList.TodoListTasks);
            //for each item in current tags, make a new tab in tab layout
        for(int i = 0; i < taskList.Tags.size(); i++){
            t.addTab(t.newTab().setText(taskList.Tags.get(i).name));
        }
        //
        //call for adapter to show list of task objects in a list
        //TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, taskList.TodoListTasks );
        TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, todo.list);
        listview.setAdapter(tAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //if a task item is clicked, code will jump here

                //pass through only primitive types of data, since classes wont work with this implementation
                    //must pass name, description, start_time, end_time, day, month, year
                viewTaskView();
            }
        });

        button = (FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTaskView();
            }
        });
        tagButton = (FloatingActionButton) findViewById(R.id.create_Tag_button);
        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { createTagView();}
        });
    }

    int current_date;
    public void openAddTaskView(){
        Intent intent = new Intent(this, AddTaskView.class);
        startActivity(intent);
    }
    public void createTagView(){
        Intent intent = new Intent(this, CreateTag.class);
        startActivity(intent);
    }
    public void viewTaskView(){
        Intent intent = new Intent(this, ViewTaskView.class);
        startActivity(intent);
    }
}

