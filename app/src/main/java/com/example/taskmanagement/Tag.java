package com.example.taskmanagement;

import android.graphics.Color;

public class Tag {
    String name;
    String color;
    public void setName(String name){
        name = name;
    }
    public String getName() {
        if(!name.isEmpty())
        return name;
        else return null;
    }
    public void setColor(String color){
        color = color;
    }
    public String getColor(){
        if(!color.isEmpty())
            return color;
        else return null;
    }
}
