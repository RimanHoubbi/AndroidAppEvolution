package com.example.myfitnesstracker.model.typeconverter;

import androidx.room.TypeConverter;

import com.example.myfitnesstracker.model.Activity_log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogDataTypeConverter {

    @TypeConverter
    public String fromActivityLog(List<Activity_log> value){
        Gson gson = new Gson();
        return gson.toJson(value);
    }
    @TypeConverter
    public List<Activity_log> toActivityList(String value){
        Gson gson = new Gson();
        ArrayList<Activity_log> list = gson.fromJson(value,  new TypeToken<ArrayList<Activity_log>>(){}.getType());
        return list;
    }

}
