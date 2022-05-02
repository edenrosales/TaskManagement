package com.example.taskmanagement;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

import java.util.LinkedList;

public class Tag {
    String name;
    int color;

    //public Tag(String name){
    //    this.name = name;
        //default color is going to be purple. This is the design primary for our application
    //    this.color = ContextCompat.getColor(MainActivity.this,  com.google.android.material.R.color.design_default_color_primary);
    //}

    public Tag(String name, int color){
        this.name = name;
        this.color = color;
    }


    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setColor(int color){ this.color = color; }
    public int getColor(){
        return color;
    }

    //returns list of names of all tags in TaskList object, really used to display in spinner widget UI
    public static LinkedList<String> getListOfTagNames(LinkedList<Tag> t){
        LinkedList<String> result = new LinkedList<>();
        for(int i = 0; i < t.size(); i++){
            result.add(t.get(i).getName());
        }
        return result;
    }

    //finds name in the list of tags
    public static Tag findName(LinkedList<Tag> tags, String name){
        for(int i = 0; i < tags.size(); i++){
            if(tags.get(i).name.equals(name)){
                Tag result = tags.get(i);
                return result;
            }
        }
        return null;
    }


}