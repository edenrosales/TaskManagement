package com.example.taskmanagement;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.os.AsyncTask;


//Database class, holds all info
@TypeConverters({Converter.class})
@androidx.room.Database(entities = {Task.class, Tag.class}, version = 1) //DO NOT TOUCH
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE;
    public abstract DatabaseDAO databaseDAO();


    public synchronized static Database getInstance(Context context){
        if(INSTANCE == null){ //Making the database when does not exist
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                    , Database.class, "DATABASE_1")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private DatabaseDAO databaseDAO;
        private PopulateDbAsyncTask(Database db){
            databaseDAO = db.databaseDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseDAO.insertTask(new Task("My First Task", "Description", new Tag("", R.color.teal_700), 1, 2, 9, 9, 9999, false));
            //default tag will be all
            databaseDAO.insertTag(new Tag("All", R.color.teal_700));
            return null;
        }
    }



}
