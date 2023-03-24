package com.example.myfitnesstracker.model.typeconverter;

import androidx.room.TypeConverter;

import com.example.myfitnesstracker.model.SensorData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class SensorDataListTypeConverter {

  @TypeConverter
    public String fromSensorList(List<SensorData> value){
   Gson gson=new Gson();
   return gson.toJson(value);
  }

  @TypeConverter
 public List<SensorData> toSensorList(String value){
   Gson gson=new Gson();
   ArrayList<SensorData> list = gson.fromJson(value, new TypeToken<ArrayList<SensorData>>(){}.getType() );
  return  list;
  }

}
