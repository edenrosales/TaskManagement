package com.example.taskmanagement;

import android.graphics.Color;

public class Tag {
    String name;
    String color;

    public Tag(String name){
        this.name = name;
        this.color = null;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }
}