package com.example.taskmanagement;


import java.util.LinkedList;

public class TaskList {
    LinkedList<Task> Tasks = new LinkedList<>();
    LinkedList<Task> TodoListTasks = new LinkedList<>();
    LinkedList<Task> CalendarTasks = new LinkedList<>();


    //view determines the LinkedList this function searches
    //1 is Tasks
    //2 is TodoListTasks
    //3 is CalendarTasks
    public Task getTask(String name, int view){
        switch(view) {
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
        }
        return null;
    }
    //I think that this function returns the LinkedList that cooresponds to the tasks that we want? This funciton might need to sort, not sure yet
    public void getTasks(){

    }
    //this tasks filters, this means we need a sort for this
    public void filterTasks(){

    }

    //this function searches for a task and returns it
    public void searchTasks(){

    }
    //i have no idea bro
    public void getSelected(){

    }

}
