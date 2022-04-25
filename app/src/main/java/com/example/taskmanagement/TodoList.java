package com.example.taskmanagement;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.LinkedList;

public class TodoList{
    LocalDate current_date;







    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateToday(){
        current_date = LocalDate.now();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateCustom(int year, int month, int day) {
        current_date = LocalDate.of(year, month, day);
    }

    LinkedList<Task> list = new LinkedList<>();

    public void setTaskList(LinkedList<Task> input){
        list = input;
    //method will return a task list given a selected view ToDo list
    //will be organized by date

}
    public void sortTasks(int choice){
        list = TaskList.sortTasks(choice, list);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void filterTasks(){
        list = TaskList.filterTasksByDate(list,current_date,1);
    }
    public void displayTaskList(){
    //method will show the tasks obtained from getTaskList() method in a to-do-list fashion
    }

}
