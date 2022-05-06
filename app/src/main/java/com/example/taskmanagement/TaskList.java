package com.example.taskmanagement;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


//import javax.lang.model.util.ElementScanner14;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TaskList {
    LinkedList<Task> Tasks = new LinkedList<>();
    LinkedList<Task> TodoListTasks = new LinkedList<>();
    LinkedList<Task> CalendarTasks = new LinkedList<>();
    LinkedList<Tag> Tags = new LinkedList<>();


    //view determines the LinkedList this function searches
    //1 is Tasks
    //2 is TodoListTasks
    //3 is CalendarTasks
    public TaskList(LinkedList<Task> input){
        this.Tasks = input;
    }
    public LinkedList<Task> getTaskList(){
        return Tasks;
    }
    public LinkedList<Task> getTodoListTasks(){
        return TodoListTasks;
    }
    public LinkedList<Task> getCalendarTasks(){ return CalendarTasks; }
    public LinkedList<Tag> getTags(){return Tags;}


    //empty taskList()
    public TaskList(){
        
    }

    //Might implement a trie tree later to make this more in depth.
    public Task getTask(String name, int view){
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

    //
    public static LinkedList<Task> getTasks(LinkedList<Task> list,Tag type){
        LinkedList<Task> results=  new LinkedList<>();
        for(int i =0; i<list.size();i++){
            if(list.get(i).associated_tag.name.equals(type.name)){
                results.add(list.get(i));
            }
        }
        return results;
    }

    //this tasks filters, this means we need a sort for this
    //by alphabetical order or something idk.
    //filter by current parameters: alphabetial, tag,

    //TodoList.setTasks(TaskList.sort(TaskList.getTaskList());

    //i changed this to static so that it might work better with the other classes that we have made so far
    public static LinkedList<Task> sortTasks(int setting, LinkedList<Task> list){
        LinkedList<Task> result = new LinkedList<>();
        switch(setting){
            case 0://this is default
                //default can be organized by dates
                return null;
            case 1://this is alphabetical
            {
                while(list.size() >0){
                    String smallest= list.get(0).name;
                    Task smallestTask = list.get(0);
                    int smallestIndex = 0;
                    for(int j =0; j<list.size();j++){
                        if(smallest.compareTo(list.get(j).name)>0) {
                            smallest = list.get(j).name;
                            smallestTask= list.get(j);
                            smallestIndex = j;
                        }
                    }
                    result.add(smallestTask);
                    list.remove(smallestIndex);
                }
                return result;
            }
            case 2: {
                //this is by tag
                //first we will get all the tags that exist
                //then they will all get sorted in alphabetical order
                //finally, they will be added in order by tag

                //we use a hashset to make this O(n)
                HashSet<Tag> tags = new HashSet<>();
                //this is getting all the tags
                for (int i = 0; i < list.size(); i++) {
                    if (!tags.contains(list.get(i).associated_tag)) {
                        tags.add(list.get(i).associated_tag);
                    }
                }
                LinkedList<Tag> tempTagList = new LinkedList<>(tags);
                LinkedList<Tag> tagList = new LinkedList<>();
                while(tempTagList.size() >0) {
                    String smallestString = tempTagList.get(0).getName();
                    Tag smallestTag = tempTagList.get(0);
                    int smallestIndex = 0;
                    for (int j = 0; j < tempTagList.size(); j++) {
                        if (tempTagList.get(j).getName().compareTo(smallestString) < 0) {
                            smallestString = tempTagList.get(j).getName();
                            smallestTag = tempTagList.get(j);
                            smallestIndex =j;
                        }
                    }
                    tagList.add(smallestTag);
                    tempTagList.remove(smallestIndex);
                }
                //now we have a sorted list of Tags in the tagList LinkedList

                //we need to sort the tasks by the Tag they are associated with
                //I think that we also need to remember to NOT use the original Task LinkedList because we would need to delete the entries from the LinkedList
                //and we are not allowed to do that.
                LinkedList<Task> temp = list;
                for (int i = 0; i < tagList.size(); i++) {
                    boolean taskFound = true;
                    while (taskFound) {
                        taskFound = false;
                        String smallestString = null;
                        Task smallestTask = null;
                        int smallestIndex=-1;
                        for (int j = 0; j < list.size(); j++) {
                            if (temp.get(j).associated_tag.equals(tagList.get(i))) {
                                taskFound = true;
                                if (smallestString == null) {
                                    smallestString = temp.get(j).name;
                                    smallestTask = temp.get(j);
                                    smallestIndex = j;
                                }
                                if (smallestString.compareTo(temp.get(j).getName()) > 0) {
                                    smallestString = temp.get(j).name;
                                    smallestTask = temp.get(j);
                                    smallestIndex =j;
                                }

                            }
                        }
                        if(taskFound && smallestIndex!=-1){
                            temp.remove(smallestIndex);
                            result.add(smallestTask);
                        }
                    }
                }
//                return list;
                return result;
            }
        }
        return null;
    }
    //this will get rid of all the Tasks that are not for the view/date in a given linkedlist
    //0 will be for calendar view
    //1 will be for the todolist view
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LinkedList<Task> filterTasksByDate(LinkedList<Task> input, LocalDate date, int setting){
        if(setting == 0){
            for(int i =input.size()-1; i>0;i--){
                if(input.get(i).due_date.getMonthValue() !=  date.getMonthValue()){
                    input.remove(i);
                }
            }
        }
        else if(setting ==1){
            //indices in linked list are {0,1,2...,LL.size() - 1}
            for(int i =input.size()-1; i>=0;i--){
                //we check if the Local Date object of each Task is before today's date, we get rid of it from the input list
                if(input.get(i).due_date.isBefore(date)){
                //if(!input.get(i).due_date.equals(date)){
                    input.remove(i);
                }
            }
        }
        return input;
    }

    //method to return the set of active tags... NOTE input, must be filtered by date first
    public static LinkedList<Tag> removeDuplicateTags(LinkedList<Task> input){
        LinkedList<Tag> output = new LinkedList<>();
        for(int i = 0; i < input.size(); i++){
            //if the output list does not have task, tag, we add
            if(!output.contains(input.get(i).associated_tag)){
                //if(){
                    output.add(input.get(i).associated_tag);

                //}
            }
            //if its already inside, we continue
        }
        return output;
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
    //this will return a list of tags for the user to use in displaying
    public static LinkedList<Tag> parseTags(LinkedList<Task> input){
        LinkedList<Tag> output = new LinkedList<>();
        Tag current = null;
        for(int i =0; i<input.size();i++){//we dont have to worry about a 0 input...all tasks have some sort of tag
            if(current == null || !current.equals(input.get(i).getTag())){
                output.add(input.get(i).getTag());
                current = input.get(i).getTag();
            }
        }
        return output;
    }
    //returns true if the task was deleted, false if not
    //deletes the first task that is equal to the one input into the funciton
    public Boolean deleteTask(Task to_delete){
        for(int i =0; i<this.Tasks.size(); i++){
            if(this.Tasks.get(i).equals(to_delete)){
                this.Tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteTag(Tag to_delete){
        for(int i =0; i<this.Tags.size();i++){
            if(this.Tags.get(i).equals(to_delete)){
                this.Tags.remove(i);
                return true;
            }
        }
        return false;
    }

}
////this is to convert this string into a localdate
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//LocalDate date = LocalDate.parse("5/5/2022",formatter);
//
////this is how to convert a LocalDate to a string
////this is a LocalDate object that contains the date "5/5/2022"
//String formattedDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
