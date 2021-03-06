package com.example.taskmanagement;

import java.util.HashMap;
import java.util.HashSet;
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
        LinkedList<Task> T = new LinkedList<>();
        switch(view){
            case 1:
                T = this.Tasks;
                break;
            case 2: 
                T = this.TodoListTasks;
                break;
                //return null;
            case 3:
                T = this.CalendarTasks;
                break;
                //return null;
        }
        for(int i = 0; i < T.size(); i++)
            if(T.get(i).name.equals(name))
                return T.get(i);
        return null;
    }
    
    //I think that this function returns the LinkedList that cooresponds to the tasks that we want? This funciton might need to sort, not sure yet
    //I think that this function returns all the tasks according to the Tag that is input
    public LinkedList<Task> getTasksByTag(Tag type){
        LinkedList<Task> results=  new LinkedList<>();
        for(int i =0; i<Tasks.size();i++){
            if(Tasks.get(i).associated_tag.name.equals(type.name)){
                results.add(Tasks.get(i));
            }
        }
        return results;
    }


    public void getTasksByTag(){

    }
    //this tasks filters, this means we need a sort for this
    //by alphabetical order or something idk.
    //filter by current parameters: alphabetial, tag,
    public LinkedList<Task> filterTasks(int setting) {
        LinkedList<Task> result = new LinkedList<>();
        switch (setting) {
            case 0://this is default
                //default can be organized by dates
                return null;

            case 1://this is alphabetical
            {
                for (int i = 0; i < Tasks.size(); i++) {
                    String smallest = Tasks.get(i).name;
                    Task smallestTask = Tasks.get(i);
                    for (int j = 0; j < Tasks.size(); j++) {
                        if (smallest.compareTo(Tasks.get(j).name) > 0) {
                            smallest = Tasks.get(j).name;
                            smallestTask = Tasks.get(j);
                        }
                    }
                    result.add(smallestTask);
                }
                return result;
            }
            case 2: {//this is by tag
//                return null;
                //first we will get all the tags that exist
                //then they will all get sorted in alphabetical order
                //finally, they will be added in order by tag

                //we use a hashset to make this O(n)
                HashSet<Tag> tags = new HashSet<>();
                //this is getting all the tags
                for (int i = 0; i < Tasks.size(); i++) {
                    if (!tags.contains(Tasks.get(i).associated_tag)) {
                        tags.add(Tasks.get(i).associated_tag);
                    }
                }
                LinkedList<Tag> tempTagList = new LinkedList<>(tags);
                LinkedList<Tag> tagList = new LinkedList<>();
                for (int i = 0; i < tempTagList.size(); i++) {
                    String smallestString = tempTagList.get(i).getName();
                    Tag smallestTag = tempTagList.get(i);
                    for (int j = 0; j < tempTagList.size(); j++) {
                        if (tempTagList.get(i).getName().compareTo(smallestString) < 0) {
                            smallestString = tempTagList.get(i).getName();
                            smallestTag = tempTagList.get(i);
                        }
                    }
                    tagList.add(smallestTag);
                }
                //now we have a sorted list of Tags in the tagList LinkedList

                //we need to sort the tasks by the Tag they are associated with
                //I think that we also need to remember to NOT use the original Task LinkedList because we would need to delete the entries from the LinkedList
                //and we are not allowed to do that.
                LinkedList<Task> temp = Tasks;
                boolean taskFound = true;
                for (int i = 0; i < tagList.size(); i++) {
                    while (taskFound) {
                        taskFound = false;
                        String smallestString = null;
                        Task smallestTask = null;
                        for (int j = 0; j < Tasks.size(); j++) {
                            if (temp.get(j).associated_tag.equals(tagList.get(i))) {
                                taskFound = true;
                                if (smallestString == null) {
                                    smallestString = temp.get(j).name;
                                    smallestTask = temp.get(j);
                                }
                                if (smallestString.compareTo(temp.get(j).getName()) > 0) {
                                    smallestString = temp.get(j).name;
                                    smallestTask = temp.get(j);
                                }

                            }
                        }
                        temp.remove(smallestTask);
                        result.add(smallestTask);
                    }
                }
                return temp;
            }
        }
        return null;
    }


    //im not really sure why this was added, i dont know what it adds that is better than the one above
//    public void filterTasks(){
//
//                //we use a hashset to make this O(n)
//                HashSet<Tag> tags = new HashSet<>();
//                //this is getting all the tags
//                for(int i =0; i<Tasks.size();i++)
//                {
//                    if(!tags.contains(Tasks.get(i).associated_tag))
//                    {
//                        tags.add(Tasks.get(i).associated_tag);
//                    }
//                }
//                LinkedList<Tag> tempTagList = new LinkedList<>(tags);
//                LinkedList<Tag> tagList = new LinkedList<>();
//                for(int i=0; i<tempTagList.size();i++)
//                {
//                    String smallestString = tempTagList.get(i).getName();
//                    Tag smallestTag = tempTagList.get(i);
//                    for(int j =0; j<tempTagList.size();j++)
//                    {
//                        if(tempTagList.get(i).getName().compareTo(smallestString) < 0)
//                        {
//                            smallestString = tempTagList.get(i).getName();
//                            smallestTag = tempTagList.get(i);
//                        }
//                    }
//                    tagList.add(smallestTag);
//                }
//                //now we have a sorted list of Tags in the tagList LinkedList
//
//                //we need to sort the tasks by the Tag they are associated with
//                //I think that we also need to remember to NOT use the original Task LinkedList because we would need to delete the entries from the LinkedList
//                //and we are not allowed to do that.
//                LinkedList<Task> temp = Tasks;
//                boolean taskFound = true;
//                for(int i =0;i<tagList.size();i++)
//                {
//                    while(taskFound)
//                    {
//                        taskFound = false;
//                        String smallestString = null;
//                        Task smallestTask = null;
//                        for(int j=0;j<Tasks.size();j++)
//                        {
//                            if(temp.get(j).associated_tag.equals(tagList.get(i)))
//                            {
//                                taskFound =true;
//                                if(smallestString == null)
//                                {
//                                    smallestString = temp.get(j).name;
//                                    smallestTask = temp.get(j);
//                                }
//                                if(smallestString.compareTo(temp.get(j).getName()) > 0)
//                                {
//                                    smallestString = temp.get(j).name;
//                                    smallestTask = temp.get(j);
//                                }
//
//                            }
//                        }
//                        temp.remove(smallestTask);
//                        result.add(smallestTask);
//                    }
//                }
//                return temp;
//        //return null;
//        return null;
//    }


    //this function searches for a task and returns it
    //Might implement a trie tree later to make this more in depth, but otherwise its the same as the getTask function
    //in the future, what will happen is that it will return a LinkedList

    //i dont think that this function will work as intended, this will pull ALL the Tasks that have the SAME EXACT name as the one passed into the function, and I think that we dont really add
    //tasks that have the same name ususally, and if we do, it is rare for a person to do this
    //i think that it will be better to implement this through the UI and use the filterTasks function for this instead
    public LinkedList<Task> searchTasks(String name, int view){
        LinkedList<Task> results = new LinkedList<>();
        //search will return a set of maximum 20 results to display... this can be all subject to change
        //search feature is not implemented fully
        for(int i = 0; i < 20; i++)
            results.add(getTask(name, view));
        return results;
        //return getTask(name, view);
    }
//    //i have no idea bro
//    public void searchTasks(){
//
//    }

//    @Test

}
