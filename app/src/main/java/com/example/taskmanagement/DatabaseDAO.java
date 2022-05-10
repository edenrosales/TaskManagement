package com.example.taskmanagement;

import java.util.List;

import androidx.lifecycle.LiveData;
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
    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task); //Will fix this soon, some reason, editing does not save

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT*FROM task_table ORDER BY start_time DESC")
    LiveData<List<Task>> getAllTasks();

    @Insert
    void insertTag(Tag tag);

    @Delete
    void deleteTag(Tag tag);

    @Update
    void updateTag(Tag tag);

    //gets tags listed in aplhabetical order
    @Query("SELECT*FROM tag_table ORDER by NAME ASC")
    LiveData<List<Tag>> getAllTags();
}
