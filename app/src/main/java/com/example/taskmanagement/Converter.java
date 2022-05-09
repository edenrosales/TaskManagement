package com.example.taskmanagement;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converter {

@TypeConverter
    public static Tag toChange(String ss){
    return ss == null ? null : new Tag(ss);
}

@TypeConverter
    public static Tag toChangeToParcel(Parcel parcel){
        return parcel == null ? null : new Tag(parcel);
    }

@TypeConverter
    public static String chae(Tag t){ return t == null ? null : t.getName(); }

    //@TypeConverter
   // public static int color(Tag t){return t == null ? null : t.getColor();}

    @TypeConverter
    public static LocalDate toDate(String dateString){
        return dateString == null ? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String toDateString(LocalDate date){
        return date == null ? null : date.toString();
    }
}
