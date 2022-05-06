package com.example.taskmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;


public class TaskViewModel extends AndroidViewModel{
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Tag>> allTags;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
        allTags = repository.getAllTags();
    }

    //task operations
    public void insertTask(Task task){
        repository.insertTask(task);
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }

    public void deleteTask(Task task){
        repository.deleteTask(task);
    }

    public void deleteAllTasks(){
        repository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    //tag operations
    public void insertTag(Tag tag){
        repository.insertTag(tag);
    }

    public void updateTag(Tag tag){
        repository.updateTag(tag);
    }

    public void deleteTag(Tag tag){
        repository.deleteTag(tag);
    }

    //no delete all tags... we skip

    public LiveData<List<Tag>> getAllTags(){
        return allTags;
    }
}

