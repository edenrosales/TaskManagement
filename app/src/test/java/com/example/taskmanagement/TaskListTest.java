package com.example.taskmanagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

class TaskListTest {

    @Test
    void sortTasksSettingOne() {
        //Tag tagOne = new Tag();
        //tagOne.setName("School");
        //tagOne.setColor("Green");
        //Tag tagTwo = new Tag();
        //tagTwo.setName("Work");
        //tagTwo.setColor("Blue");
        //Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,1,1,2022,false);
        //Task taskTwo = new Task("A332 Lab Work","Do some work",tagOne, 0,1,1,1,2022,false);
        //Task taskThree = new Task("CWork on pictures", "Do some work", tagTwo, 0,1,1,1,2022,false);
        //Task taskFour = new Task("BBuy products", "Do some work", tagTwo, 0,1,1,1,2022,true);
        //Task taskFive = new Task("AMake pancakes", "Do some work", tagTwo, 0,1,1,1,2022,true);
        //LinkedList<Task> input = new LinkedList<>();
        //input.add(taskOne);
        //input.add(taskTwo);
        //input.add(taskThree);
        //input.add(taskFour);
        //input.add(taskFive);
        //LinkedList<Task> list = new LinkedList<>();
        //list = TaskList.sortTasks(2,input);
        //Tag tagThree = new Tag();
//        assertEquals("B380 project",list.get(0).getName());
//        assertEquals("A332 Lab Work",list.get(1).getName());
//        assertEquals("CWork on pictures",list.get(2).getName());
//        assertEquals("BBuy products",list.get(3).getName());
//        assertEquals("AMake pancakes",list.get(4).getName());

    }
    @Test
    void deleteTask(){
        Tag tagOne = new Tag("School",0);
        Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,1,1,2022,false);
        TaskList main = new TaskList();
        main.Tasks.add(taskOne);
//        main.deleteTask(taskOne);
        assertEquals(null,main.Tasks);

    }
    @Test
    void sortTasksSettingTwo() {

        /*Tag tagOne = new Tag();
        tagOne.setName("School");
        tagOne.setColor("Green");
        Tag tagTwo = new Tag();
        tagTwo.setName("Work");
        tagTwo.setColor("Blue");
        Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,1,1,2022,false);
        Task taskTwo = new Task("A332 Lab Work","Do some work",tagOne, 0,1,1,1,2022,false);
        Task taskThree = new Task("CWork on pictures", "Do some work", tagTwo, 0,1,1,1,2022,false);
        Task taskFour = new Task("BBuy products", "Do some work", tagTwo, 0,1,1,1,2022,true);
        Task taskFive = new Task("AMake pancakes", "Do some work", tagTwo, 0,1,1,1,2022,true);
        LinkedList<Task> input = new LinkedList<>();
        input.add(taskOne);
        input.add(taskTwo);
        input.add(taskThree);
        input.add(taskFour);
        input.add(taskFive);
        LinkedList<Task> list = new LinkedList<>();
        input= TaskList.sortTasks(2,input);
        assertEquals("A332 Lab Work",input.get(0).getName());
        assertEquals("B380 project",input.get(1).getName());
        assertEquals("AMake pancakes",input.get(2).getName());
        assertEquals("BBuy products",input.get(3).getName());
        assertEquals("CWork on pictures",input.get(4).getName());
//        assertEquals("B380 project",list.get(0).getName());
//        assertEquals("A332 Lab Work",list.get(1).getName());
//        assertEquals("CWork on pictures",list.get(2).getName());
//        assertEquals("BBuy products",list.get(3).getName());
//        assertEquals("AMake pancakes",list.get(4).getName());
*/
    }
    @Test
    void filterTasksByDateOne() {
      /*
        Tag tagOne = new Tag();
        tagOne.setName("School");
        tagOne.setColor("Green");
        Tag tagTwo = new Tag();
        tagTwo.setName("Work");
        tagTwo.setColor("Blue");
        Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,2,1,2022,false);
        Task taskTwo = new Task("A332 Lab Work","Do some work",tagOne, 0,1,1,1,2022,false);
        Task taskThree = new Task("CWork on pictures", "Do some work", tagTwo, 0,1,1,1,2022,false);
        Task taskFour = new Task("BBuy products", "Do some work", tagTwo, 0,1,2,1,2022,true);
        Task taskFive = new Task("AMake pancakes", "Do some work", tagTwo, 0,1,2,1,2022,true);
        LinkedList<Task> input = new LinkedList<>();
        input.add(taskOne);
        input.add(taskTwo);
        input.add(taskThree);
        input.add(taskFour);
        input.add(taskFive);
        input = TaskList.sortTasks(2,input);
        LocalDate date = LocalDate.of(2022,1,1);
        input = TaskList.filterTasksByDate(input,date ,1 );
        assertEquals("A332 Lab Work",input.get(0).getName());
        assertEquals("CWork on pictures", input.get(1).getName());
    */
    }

    @Test
    void filterTasksByDateTwo() {
        /*Tag tagOne = new Tag();
        tagOne.setName("School");
        tagOne.setColor("Green");
        Tag tagTwo = new Tag();
        tagTwo.setName("Work");
        tagTwo.setColor("Blue");
        Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,2,2,2022,false);
        Task taskTwo = new Task("A332 Lab Work","Do some work",tagOne, 0,1,1,1,2022,false);
        Task taskThree = new Task("CWork on pictures", "Do some work", tagTwo, 0,1,1,1,2022,false);
        Task taskFour = new Task("BBuy products", "Do some work", tagTwo, 0,1,2,1,2022,true);
        Task taskFive = new Task("AMake pancakes", "Do some work", tagTwo, 0,1,2,1,2022,true);
        LinkedList<Task> input = new LinkedList<>();
        input.add(taskOne);
        input.add(taskTwo);
        input.add(taskThree);
        input.add(taskFour);
        input.add(taskFive);
        input = TaskList.sortTasks(2,input);
        LocalDate date = LocalDate.of(2022,1,1);
        input = TaskList.filterTasksByDate(input,date ,0 );
        Tag tagThree = new Tag();
    */}
    @Test
    void parseTags() {
        /*Tag tagOne = new Tag();
        tagOne.setName("School");
        tagOne.setColor("Green");
        Tag tagTwo = new Tag();
        tagTwo.setName("Work");
        tagTwo.setColor("Blue");
        Task taskOne = new Task("B380 project","Do some work", tagOne,0,1,2,2,2022,false);
        Task taskTwo = new Task("A332 Lab Work","Do some work",tagOne, 0,1,1,1,2022,false);
        Task taskThree = new Task("CWork on pictures", "Do some work", tagTwo, 0,1,1,1,2022,false);
        Task taskFour = new Task("BBuy products", "Do some work", tagTwo, 0,1,2,1,2022,true);
        Task taskFive = new Task("AMake pancakes", "Do some work", tagTwo, 0,1,2,1,2022,true);
        LinkedList<Task> input = new LinkedList<>();
        input.add(taskOne);
        input.add(taskTwo);
        input.add(taskThree);
        input.add(taskFour);
        input.add(taskFive);
        input = TaskList.sortTasks(2,input);
        LinkedList<Tag> tags = TaskList.parseTags(input);
        assertEquals("School",tags.get(0).getName());
        assertEquals("Blue",tags.get(1).getColor());
   */ }
}