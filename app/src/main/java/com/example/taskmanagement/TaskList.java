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

    //Might implement a trie tree later to make this more in depth.
    public Task getTask(String name, int view){
        switch(view) {
            case 1:
                for(int i =0; i<Tasks.size();i++){
                    if(Tasks.get(i).name.equals(name))
                        return Tasks.get(i);
                }
            case 2:
                for(int i =0; i<TodoListTasks.size();i++){
                    if(TodoListTasks.get(i).name.equals(name))
                        return TodoListTasks.get(i);
                }
            case 3:
                for(int i =0; i<CalendarTasks.size();i++){
                    if(CalendarTasks.get(i).name.equals(name))
                        return CalendarTasks.get(i);
                }
        }
        return null;
    }
    //I think that this function returns the LinkedList that cooresponds to the tasks that we want? This funciton might need to sort, not sure yet
    //I think that this function returns all the tasks according to the Tag that is input
    public LinkedList<Task> getTasks(Tag type){
        LinkedList<Task> results=  new LinkedList<>();
        for(int i =0; i<Tasks.size();i++){
            if(Tasks.get(i).associated_tag.name.equals(type.name)){
                results.add(Tasks.get(i));
            }
        }
        return results;
    }
    //this tasks filters, this means we need a sort for this
    //by alphabetical order or something idk.
    //filter by current parameters: alphabetial, tag,
    public LinkedList<Task> filterTasks(int setting){
        switch(setting){
            case 0://this is default
                
            case 1:
        }
        return null;
    }

    //this function searches for a task and returns it
    //Might implement a trie tree later to make this more in depth, but otherwise its the same as the getTask function
    //in the future, what will happen is that it will return a LinkedList
    public Task searchTasks(String name, int view){
        return getTask(name, view);

    }
    //i have no idea bro
    public void getSelected(){

    }

}
