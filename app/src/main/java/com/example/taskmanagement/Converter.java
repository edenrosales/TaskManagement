package com.example.taskmanagement;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converter {

@TypeConverter
    public static Tag toChange(String ss){
    return ss == null ? null : new Tag(ss);
}

@TypeConverter
    public static String chae(Tag t){ return t == null ? null : t.getName(); }

    @TypeConverter
    public static LocalDate toDate(String dateString){
        return dateString == null ? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String toDateString(LocalDate date){
        return date == null ? null : date.toString();
    }
}
