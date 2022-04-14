package com.example.taskmanagement;


import java.util.LinkedList;

//import javax.lang.model.util.ElementScanner14;

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
     /*   switch(view) {
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
    */
        LinkedList<Task> T = new LinkedList<>();
        switch(view){
            case 1:
                T = this.Tasks;
                break;
            case 2: 
                T = this.TodoListTasks;
                break;
            case 3:
                T = this.CalendarTasks;
                break;
        }
        for(int i = 0; i < T.size(); i++)
            if(T.get(i).name.equals(name))
                return T.get(i);
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
        LinkedList<Task> result = new LinkedList<>();
        switch(setting){
            case 0://this is default
                //default can be organized by dates
                return null;
            case 1://this is alphabetical
            {
                for(int i =0; i<Tasks.size();i++){
                    String smallest= Tasks.get(i).name;
                    Task smallestTask = Tasks.get(i);
                    for(int j =0; j<Tasks.size();j++){
                        if(smallest.compareTo(Tasks.get(j).name)>0) {
                            smallest = Tasks.get(j).name;
                            smallestTask= Tasks.get(j);
                        }
                    }
                    result.add(smallestTask);
                }
                return result;
            }
            case 2://this is by tag
                return null;
        }
        return null;
    }

    //this function searches for a task and returns it
    //Might implement a trie tree later to make this more in depth, but otherwise its the same as the getTask function
    //in the future, what will happen is that it will return a LinkedList
    public LinkedList<Task> searchTasks(String name, int view){
        LinkedList<Task> results = new LinkedList<>();
        //search will return a set of maximum 20 results to display... this can be all subject to change
        //search feature is not implemented fully
        for(int i = 0; i < 20; i++)
            results.add(getTask(name, view));
        return results;
        //return getTask(name, view);

    }
    //i have no idea bro
    public void getSelected(){

    }

}
