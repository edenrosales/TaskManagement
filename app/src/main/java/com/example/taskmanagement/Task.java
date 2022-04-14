package com.example.taskmanagement;

public class Task {//will add a description variable later.
    String description;
    String name;
    Tag associated_tag;
    int start_time;
    int end_time;
    int due_date;
    Boolean notify_me;

    Task(String name,String description,Tag associated_tag,int start_time,int end_time,int due_date, Boolean notify_me){
        this.name = name;
        this.description= description;
        this.associated_tag = associated_tag;
        this.end_time = end_time;
        this.due_date = due_date;
        this.notify_me = notify_me;
        this.start_time = start_time;
    }
    public void createTask(){
        //this method should invoke the setter method but it should be made so that the user only needs to put in certain parts of the function
        //for instance, if the user only puts in a name, but no tag, the method should have a case for that, and if they put a start time and no end time there should
        //also be a case for that in the code... i dont know how to do that so i will leave that to Hampig, or whoever is working on this class.
        //-Eden
    }
    public void editTask(Task task_name,String name,String description,Tag associated_tag,int start_time,int end_time,int due_date, Boolean notify_me){
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
        if(due_date!=-1)
        task_name.setDue(due_date);
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
    public void setDue(int due_date){
        this.due_date = due_date;
    }
    public int getDue(){
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
