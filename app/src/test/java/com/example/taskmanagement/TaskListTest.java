package com.example.taskmanagement;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskListTest {

    @Test
    public void getTask(){
        TaskList list2 = new TaskList();
        Task task1 = new Task("Wash Dishes", "Make sure the dishes are washed", null, 0, 10, 0, false);
        list2.TodoListTasks.add(task1);
        assertEquals(task1, list2.getTask("Wash Dishes", 2));
    }

}