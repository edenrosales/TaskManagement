//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
class TaskListTest {
    @Test
    void editTask(){
        Task Task1 = new Task("Wash Dishes","Make sure the dishes are washed",null,0,10,0,false);
        Task Task2 = new Task("Walk my dog", "Walk Finnley in the morning",null,4,5,0, false);
        Task Task3 = new Task("Work on Homework Assignment 1", "COMP 380 Assignment",null,6,7,0, false);
        TaskList List1 = new TaskList();
        List1.TodoListTasks.add(Task1);
        List1.TodoListTasks.add(Task2);
        List1.TodoListTasks.add(Task3);
        Task resultTask = new Task();
        resultTask = getTask("Walk my dog", 2);
        assertEquals("Wash Dishes",List1.getTask("Wash Dishes",2));
        //assertEquals("Make sure the dishes are washed",newTask.getDescription());
        //assertEquals(null,newTask.getTag());
        //assertEquals(0,newTask.getStart());
        //assertEquals(10,newTask.getEnd());
        //assertEquals(10,newTask.getDue());
        //assertEquals(true,newTask.getNotify());


        system.out.println("Name of Task: " + resultTask.name);
        system.out.println("Description: " + resultTask.description);
        system.out.println("Start Time: " + resultTask.start_time);
        system.out.println("End Time: "+resultTask.end_time);
    }
}
