package com.example.taskmanagement;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

////Responsible for:
//// Editing Task
//// Adding Task
//// Deleting Task
//// Reading Task


@Dao
public interface DatabaseDAO {
    @Query("SELECT * FROM task") //Reads Tasks
    List<Task> getAll();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task); //Will fix this soon, some reason, editing does not save

}
