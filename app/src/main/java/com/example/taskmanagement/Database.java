//package com.example.taskmanagement;
//
//
//import android.content.Context;
//
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
////Database class, holds all info
//
//@androidx.room.Database(entities = {Task.class}, version = 1) //DO NOT TOUCH
//public abstract class Database extends RoomDatabase {
//
//    public static Database INSTANCE;
//
//    public synchronized static Database getDatabase(Context context){
//        if(INSTANCE == null){ //Making the database when does not exist
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
//                    ,Database.class, "DATABASE_1")
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return INSTANCE;
//    }
//
//    public abstract DatabaseDAO databaseDAO();
//}
