package com.example.taskmanagement;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private DatabaseDAO databaseDAO;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Tag>> allTags;

    //repository constructor
    public TaskRepository(Application application){
        Database database = Database.getInstance(application);
        databaseDAO = database.databaseDAO();
        allTasks = databaseDAO.getAllTasks();
        allTags = databaseDAO.getAllTags();
    }

    //Repository inserts for tasks
    public void insertTask(Task task){
        new TaskRepository.InsertTaskAsyncTask(databaseDAO).execute(task);
    }

    public void updateTask(Task task){
        new TaskRepository.UpdateTaskAsyncTask(databaseDAO).execute(task);
    }

    public void deleteTask(Task task){
        new TaskRepository.DeleteTaskAsyncTask(databaseDAO).execute(task);
    }

    public void deleteAllTasks(){
        new TaskRepository.DeleteAllTasksAsyncTask(databaseDAO).execute();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private DatabaseDAO databaseDAO;

        private InsertTaskAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            databaseDAO.insertTask(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private DatabaseDAO databaseDAO;

        private UpdateTaskAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            databaseDAO.updateTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private DatabaseDAO databaseDAO;

        private DeleteTaskAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            databaseDAO.deleteTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void>{
        private DatabaseDAO databaseDAO;

        private DeleteAllTasksAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseDAO.deleteAllTasks();
            return null;
        }
    }

    //for Tags
    public void insertTag(Tag tag){
        new TaskRepository.InsertTagAsyncTask(databaseDAO).execute(tag);
    }

    public void updateTag(Tag tag){
        new TaskRepository.InsertTagAsyncTask(databaseDAO).execute(tag);
    }

    public void deleteTag(Tag tag){
        new TaskRepository.InsertTagAsyncTask(databaseDAO).execute(tag);
    }

    //we do not have delete all tags, we dont need it because the first tag is all

    public LiveData<List<Tag>> getAllTags(){
        return allTags;
    }


    //async classes for tags
    private static class InsertTagAsyncTask extends AsyncTask<Tag, Void, Void> {
        private DatabaseDAO databaseDAO;

        private InsertTagAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Tag... tags) {
            databaseDAO.insertTag(tags[0]);
            return null;
        }
    }

    private static class UpdateTagAsyncTask extends AsyncTask<Tag, Void, Void>{
        private DatabaseDAO databaseDAO;

        private UpdateTagAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Tag... tags) {
            databaseDAO.updateTag(tags[0]);
            return null;
        }
    }

    private static class DeleteTagAsyncTask extends AsyncTask<Tag, Void, Void>{
        private DatabaseDAO databaseDAO;

        private DeleteTagAsyncTask(DatabaseDAO databaseDAO){
            this.databaseDAO = databaseDAO;
        }

        @Override
        protected Void doInBackground(Tag... tags) {
            databaseDAO.deleteTag(tags[0]);
            return null;
        }
    }
}



