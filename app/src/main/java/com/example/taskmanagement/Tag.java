package com.example.taskmanagement;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

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
}