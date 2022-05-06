package com.example.taskmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    private FloatingActionButton addTaskButton;
    private FloatingActionButton tagButton;
    String name;
    String description;
    LocalDate today;
    String new_tag_name;
    boolean is_new_tag_added = false;
    //selected task will be default until a use picks a task
    //public static Task selected_task = new Task("", "", new Tag(""), 0, 0, 0, 0, 0, false);
    public static TaskList taskList = new TaskList();
    public static Tag all = new Tag("All", R.color.teal_700);
    /*******************/
    //@RequiresApi(api = Build.VERSION_CODES.O)
    public static Task selected_task = new Task("","", all, 0,0,9,9,9999, false);
    /*************************/


    public static final int ADD_TASK_REQUEST = 1;
    //public static ActivityResult ac;
    private TaskViewModel taskViewModel;
    //ADD TASK ACTIVITY RESULT LAUNCHER
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if(activityResult.getResultCode() == Activity.RESULT_OK){
                        Intent data = activityResult.getData();
                        String name = data.getStringExtra("EXTRA_NAME"); // im getting null
                        String description = data.getStringExtra("EXTRA_DESCRIPTION");
                        int start_time = data.getIntExtra("EXTRA_START", 1);
                        int end_time = data.getIntExtra("EXTRA_END", 1);
                        int day = data.getIntExtra("EXTRA_DAY", 1);
                        int month = data.getIntExtra("EXTRA_MONTH", 1);
                        int year = data.getIntExtra("EXTRA_YEAR", 1);
                        Task task = new Task(name, description, all, start_time, end_time, day, month, year, false);
                        taskViewModel.insertTask(task);
                        Toast.makeText(MainActivity.this, "Task Saved", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Task NOT Saved", Toast.LENGTH_SHORT).show();

                    }
                }
            });

    //CREATE TAG ACTIVITY RESULT LAUNCHER
    ActivityResultLauncher<Intent> createTagResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if(activityResult.getResultCode() == Activity.RESULT_OK){
                        //if tag was created
                        Intent data = activityResult.getData();
                        String name = data.getStringExtra("EXTRA_NAME");
                        //new_tag_name = name;
                        int color = data.getIntExtra("EXTRA_COLOR", 1);
                        Tag tag = new Tag(name,color);
                        taskViewModel.insertTag(tag);
                        Toast.makeText(MainActivity.this, "Tag Saved", Toast.LENGTH_SHORT).show();
                        is_new_tag_added = true;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Tag NOT Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TodoList todo = new TodoList();
        //getting todoListTasks
        //todo.list = taskList.getTodoListTasks();
        //get the current date
        //today = LocalDate.now();
        //10 tasks will be added here
        //taskList.Tags.add(new Tag("Tag 1", 42000));
        //taskList.Tags.add(new Tag("Tag 2", 32000));


        //our todoList Tasks will be filtered by date first, the last parameter, 1, is todoList View
        //taskList.TodoListTasks = filterTasksByDate(taskList.Tasks, today, 1);
        //filtering todo list tasks by date
        //todo.list = filterTasksByDate(todo.list, today, 1);
        //Need to display current input tags
        //TabLayout t = (TabLayout) findViewById(R.id.tag_tab_layout);
            //Find the all relevant tags that are represented all items in todoList...Only need one tag for display, since multiple tasks can share a tag
        //LinkedList<Tag> current_tags = parseTags(taskList.TodoListTasks);
            //for each item in current tags, make a new tab in tab layout
        //for(int i = 0; i < taskList.Tags.size(); i++){
        //    t.addTab(t.newTab().setText(taskList.Tags.get(i).name));
        //}

        //Tab bar adapter, this will give us the opportunity to access each tab item's function
        //ArrayAdapter tabAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, todo.list);
        //t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //    @Override
        /*    public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //System.out.println(R.id.all_tags);
                        //System.out.println(tab.getId());
                        //System.out.println(tab.getText().toString());
                        if(tab.getText().toString().equals("All")){
                            //this is all, we refresh main activity// it will go back to start...database isn't running so all will work properly once database is working
                            TaskBoxAdapter specialAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, todo.list);
                            listview.setAdapter(specialAdapter);
                            accessList(listview);
                        }
                        //for a specific tag
                        else{
                            ListView special_list = (ListView) findViewById(R.id.listView);
                            String name = tab.getText().toString();
                            //System.out.println(name);
                            Tag tag = Tag.findName(taskList.getTags(), name);
                            LinkedList<Task> special_tasks = getTasks(todo.getList(), tag);
                            TaskBoxAdapter specialAdapter = new TaskBoxAdapter(MainActivity.this, R.layout.each_task, special_tasks);
                            listview.setAdapter(specialAdapter);
                            accessList(listview);
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

*/




        //TabLayout t = (TabLayout) findViewById(R.id.tag_tab_layout);
        //TagBoxAdapter tagBoxAdapter = new TagBoxAdapter();
        //ArrayAdapter tabAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        //taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
        //    @Override
        //    public void onChanged(List<Tag> tags) {
                //t.addTab(t.newTab().setText(new_tag_name));
        //        tagBoxAdapter.setTags(tags);
                //t.addTab(t.newTab().setText(tags.get(tags.size()).getName()));
                //List<String> names = Tag.getListOfTagNamesLiveData(tags);
                //if(is_new_tag_added){
                //    t.addTab(t.newTab().setText(names.get(names.size() - 1)));
                //}
                //for(int i = 0;  i < names.size(); i++){
                //    t.addTab(t.newTab().setText(names.get(i)));
                //}
                //here all tabs wil be made
       //     }
       // });
        //TAB LAYOUT FOR TAGS
        //List<Tag> tags = tagBoxAdapter.getTags();
        //List<Tag> tags = taskViewModel.getAllTags().getValue();
        //for(int i = 0; i < tags.size(); i++){
        //    t.addTab(t.newTab().setText(tags.get(i).getName()));
        //}
        //LiveData<List<Tag>> tags = taskViewModel.getAllTags();
        //LiveData<List<String>> tag_names = Tag.getListOfTagNamesLiveData(tags);
        //couldnot find a method to add all items of list as a tab so we have to do it linearly
        //List<String>list_tag_names = (List) tag_names;
        //for(int i = 0; i < list_tag_names.size(); i++){
            //make a tab for each item in the list of tags
        //    t.addTab(t.newTab().setText(list_tag_names.get(i)));
        //}


        //RECYCLER VIEW FOR TASKS
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        TaskBoxAdapter adapter = new TaskBoxAdapter();
        recyclerView.setAdapter(adapter);
        //taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        //this is where we need to filter the tasks by date
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
              adapter.setTasks(tasks);
            }
        });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.tag_tab_layout);
        myList.setLayoutManager(layoutManager);
        myList.setHasFixedSize(true);
        TagBoxAdapter tagBoxAdapter1 = new TagBoxAdapter();
        myList.setAdapter(tagBoxAdapter1);
        taskViewModel.getAllTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                tagBoxAdapter1.setTags(tags);
            }
        });




        addTaskButton = (FloatingActionButton) findViewById(R.id.button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openAddTaskView();
                Intent intent = new Intent(MainActivity.this, AddTaskView.class);
                activityResultLauncher.launch(intent);
            }
        });
        tagButton = (FloatingActionButton) findViewById(R.id.create_Tag_button);
        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateTag.class);
                //createTagResultLauncher.launch(intent);
                createTagResultLauncher.launch(intent);
            }
        });
    }

    int current_date;
    public void createTagView(){
        //Intent intent = new Intent(this, CreateTag.class);
        //startActivity(intent);
        Intent intent = new Intent(MainActivity.this, CreateTag.class);
        createTagResultLauncher.launch(intent);
    }
    public void viewTaskView(){
        Intent intent = new Intent(this, ViewTaskView.class);
        startActivity(intent);
    }
    

    
}

