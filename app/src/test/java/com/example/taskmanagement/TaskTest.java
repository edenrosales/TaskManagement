package com.example.taskmanagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void createTask() {
        Tag temp = new Tag();
        temp.setName("School");
        temp.setColor("Green");
        Task newTask = new Task("Do 380 project","Do some work", temp,0,1,1,1,2022,false);
        assertEquals("Do 380 project",newTask.getName());
        assertEquals("Do some work", newTask.getDescription());
        assertEquals(temp,newTask.getTag());
        assertEquals(0,newTask.getStart());
        assertEquals(1,newTask.getEnd());
    }

    @Test
    void editTask() {
        Tag temp = new Tag();
        temp.setName("School");
        temp.setColor("Green");
        Task newTask = new Task("Do 380 project","Do some work", temp,0,1,1,1,2022,false);
        Tag newTag = new Tag();
        newTag.setName("Work");
        newTask.editTask(newTask,null,null,newTag,-1,-1,-1,-1,-1,true);
        assertEquals(newTag, newTask.getTag());
    }
}