package com.example.taskmanagement;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Calendar {
    LocalDate current_date;
    LinkedList<Task> list = new LinkedList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateToday(){
        current_date = LocalDate.now();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateCustom(int year, int month, int day){
        current_date = LocalDate.of(year, month,day);
    }

    public void setTaskList(LinkedList<Task> input){
        list = input;
    }

    public void sortTasks(int choice){
        list = TaskList.sortTasks(choice,list);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void filterTasks(){
        list = TaskList.filterTasksByDate(list,current_date,0);
    }

    public void viewPresent(){
    //method will show the current calendar day in the calendar view
    }

    public void getTaskList(ArrayList<Tag> tags){
    //same method as getTaskList() in to-do List except this is optimized for calendar view, so sorted by starting and ending time
    }

    public void displayCalendar(){
    //method to invoke the calendar view. This function will show all the necessary data that needs to be displayed at a given moment
    }
}
