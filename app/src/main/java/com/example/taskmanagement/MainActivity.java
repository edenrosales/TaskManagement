package com.example.taskmanagement;
import static com.example.taskmanagement.TaskList.filterTasksByDate;
import static com.example.taskmanagement.TaskList.getTasks;
import static com.example.taskmanagement.TaskList.parseTags;
import static com.example.taskmanagement.TaskList.removeDuplicateTags;
import static com.example.taskmanagement.TaskList.sortTasks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private FloatingActionButton tagButton;
    //ListView listview;

    String name;
    String description;
    LocalDate today;
    //selected task will be default until a use picks a task
    //@RequiresApi(api = Build.VERSION_CODES.O)
    //public static Task selected_task = new Task("", "", new Tag(""), 0, 0, 0, 0, 0, false);
    public static TaskList taskList = new TaskList();
    public static Tag all = new Tag("All", R.color.teal_700);
    /*******************/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Task selected_task = new Task("","", all, 0,0,9,9,9999, false);
    /*************************/
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listview = (ListView) findViewById(R.id.listView);
        TodoList todo = new TodoList();
        //getting todoListTasks
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        //10 tasks will be added here
        taskList.Tags.add(new Tag("Tag 1", 42000));
        taskList.Tags.add(new Tag("Tag 2", 32000));
        taskList.Tasks.add(new Task("Task 1", "Description 1", taskList.Tags.get(0), 8, 10, 5,5,2022,false));
        taskList.Tasks.add(new Task("Task 2", "Description 2", taskList.Tags.get(0), 11, 12, 5,5,2022,false));
        taskList.Tasks.add(new Task("Task 3", "Description 3", taskList.Tags.get(1), 1, 2, 5,5,2022,false));
        todo.list = taskList.Tasks;
        today = LocalDate.now();
        //our todoList Tasks will be filtered by date first, the last parameter, 1, is todoList View
        //taskList.TodoListTasks = filterTasksByDate(taskList.Tasks, today, 1);
        //filtering todo list tasks by date
        todo.list = filterTasksByDate(todo.list, today, 1);
        todo.tags = parseTags(todo.list);
        //Need to display current input tags
        TabLayout t = (TabLayout) findViewById(R.id.tag_tab_layout);
            //Find the all relevant tags that are represented all items in todoList...Only need one tag for display, since multiple tasks can share a tag
        //LinkedList<Tag> current_tags = parseTags(taskList.TodoListTasks);
            //for each item in current tags, make a new tab in tab layout
        for(int i = 0; i < taskList.Tags.size(); i++){
            t.addTab(t.newTab().setText(taskList.Tags.get(i).name));
        }

        recyclerAdapter adapter = new recyclerAdapter(this,todo.getList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Tab bar adapter, this will give us the opportunity to access each tab item's function
        ArrayAdapter tabAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, todo.list);
        t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(R.id.all_tags);
                        System.out.println(tab.getId());
                        System.out.println(tab.getText().toString());
                        if(tab.getText().toString().equals("All")){
                            //this is all, we refresh main activity// it will go back to start...database isn't running so all will work properly once database is working
                            recyclerAdapter adapter = new recyclerAdapter(MainActivity.this,todo.getList());
                            recyclerView.setAdapter(adapter);
                            //accessList(listview); this is for opening up view
                        }
                        //for a specific tag
                        else{
//                            ListView special_list = (ListView) findViewById(R.id.listView);
                            String name = tab.getText().toString();
                            //System.out.println(name);
                            Tag tag = Tag.findName(taskList.getTags(), name);
                            LinkedList<Task> special_tasks = getTasks(todo.getList(), tag);
                            recyclerAdapter adapter = new recyclerAdapter(MainActivity.this,special_tasks);
                            recyclerView.setAdapter(adapter);

                            //accessList(listview); this was for opening up view
                        }
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //call for adapter to show list of task objects in a list
        //TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, taskList.TodoListTasks );
//        TaskBoxAdapter tAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, todo.list);
//        listview.setAdapter(tAdapter);

        //
//        accessList(listview);
        //

        //listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        //        //if a task item is clicked, code will jump here
        //
        //        //pass through only primitive types of data, since classes wont work with this implementation
        //            //must pass name, description, start_time, end_time, day, month, year
        //        viewTaskView();
        //    }
        //});

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
    
    //method will be called both by tab selection and no tab selected
    public void accessList(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //if a task item is clicked, code will jump here
                //pass through only primitive types of data, since classes wont work with this implementation
                //must pass name, description, start_time, end_time, day, month, year
                selected_task = taskList.Tasks.get(i);
                viewTaskView();
            }
        });
    }
}

