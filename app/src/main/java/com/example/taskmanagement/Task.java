package com.example.taskmanagement;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid; //Autogenerated; DO NOT TOUCH

    //**Variables that are going to be stored will be marked with a '@'. If missing something, let me (Pouria) know**

    @ColumnInfo(name = "description")
    String description;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "tag")
    Tag associated_tag;

    int start_time;
    int end_time;
    int due_date;
    Boolean notify_me;
    Task(String name,Tag associated_tag,int start_time,int end_time,int due_date, Boolean notify_me){
        name = name;
        associated_tag = associated_tag;
        end_time = end_time;
        due_date = due_date;
        notify_me = notify_me;
        start_time = start_time;
    }

    public void createTask(){
        //this method should invoke the setter method but it should be made so that the user only needs to put in certain parts of the function
        //for instance, if the user only puts in a name, but no tag, the method should have a case for that, and if they put a start time and no end time there should
        //also be a case for that in the code... i dont know how to do that so i will leave that to Hampig, or whoever is working on this class.
        //-Eden
    }

    public void setName(String name){
        name = name;
    }
    public String getName(){
        if(!name.isEmpty()){
            return name;
        }
        else return null;
    }
    public void setTag(Tag associated_tag){
        associated_tag = associated_tag;
    }
    public Tag getTag(){
        if(associated_tag!=null)
            return associated_tag;
        else return null;
    }
    public void setStart(int start_time){
        start_time = start_time;
    }
    public int getStart(){
        return start_time;
    }
    public void setEnd(int end_time){
        end_time = end_time;
    }
    public int getEnd(){
        return end_time;
    }
    public void setDue(int due_date){
        due_date = due_date;
    }
    public int getDue(){
        return due_date;
    }
    public void setNotify(Boolean notify_me){
        notify_me = notify_me;
    }
    public Boolean getNotify(){
        return notify_me;
    }
    //this function should go to the database and delete a task, if it is successful, then it should return true, otherwise false.
    //we could associate an error message to see what the problem is if it does not delete
    public Boolean deleteTask(String task_name){
        return true;
    }


}
