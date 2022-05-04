package com.example.taskmanagement;


import android.os.Build;

import java.io.Serializable;
import java.time.LocalDate;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Task.task
//Will be used with the Database class
@Entity
public class Task implements Serializable {//will add a description variable later.
        @PrimaryKey(autoGenerate = true)
    private int uid; //Autogenerated; DO NOT TOUCH

    //**Variables that are going to be stored will be marked with a '@'. If missing something, let me (Pouria) know**

        @ColumnInfo(name = "description")
    String description;

        @ColumnInfo(name = "name")
    String name;

        @ColumnInfo(name = "tag")
    Tag associated_tag;

    //I don't know if storing these will be needed, not sure...
    int start_time;
    int end_time;
    LocalDate due_date;
    Boolean notify_me;
    Boolean completed = false;


    @RequiresApi(api = Build.VERSION_CODES.O)
    Task(String name, String description, Tag associated_tag, int start_time, int end_time, int day, int month, int year, Boolean notify_me){
        this.name = name;
        this.description= description;
        this.associated_tag = associated_tag;
        this.end_time = end_time;//minutes from 0:00
        this.due_date = LocalDate.of(year,month,day);
        this.notify_me = notify_me;
        this.start_time = start_time; //in minutes from 0:00
    }
    public void completeTask(){
        this.completed = true;
    }
    public void uncompleteTask(){
        this.completed = false;
    }
    public Boolean getCompleted(){
        return this.completed;
    }
    public void createTask(){
        //deprecated. use constructor
    }
    //Might be changed since editing a Task will have to do with a Database method
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void editTask(Task task_name, String name, String description, Tag associated_tag, int start_time, int end_time, int day, int month, int year, Boolean notify_me){
        if(name!=null)
            task_name.setName(name);
        if(description!=null)
            task_name.setDescription(description);
        if(associated_tag!=null)
            task_name.setTag(associated_tag);
        if(start_time!=-1)
            task_name.setStart(start_time);
        if(end_time!=-1)
            task_name.setEnd(end_time);
        if(day != -1 && month != -1 && year != -1)
            task_name.setDue(year,month,day);
        if(notify_me!=false)
            task_name.setNotify(notify_me);
    }
    public String getDescription(){
        if(!description.isEmpty()){
            return description;
        }
        else return null;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        if(!name.isEmpty()){
            return name;
        }
        else return null;
    }
    public void setTag(Tag associated_tag){
        this.associated_tag = associated_tag;
    }
    public Tag getTag(){
        if(associated_tag!=null)
            return associated_tag;
        else return null;
    }
    public void setStart(int start_time){
        this.start_time = start_time;
    }
    public int getStart(){
        return start_time;
    }
    public void setEnd(int end_time){
        this.end_time = end_time;
    }
    public int getEnd(){
        return end_time;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDue(int year, int month, int day){
        this.due_date = LocalDate.of(year,month,day);
    }
    public LocalDate getDue(){
        return due_date;
    }
    public void setNotify(Boolean notify_me){
        this.notify_me = notify_me;
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