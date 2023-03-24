package com.example.myfitnesstracker.model;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myfitnesstracker.model.typeconverter.ActivityLogDataTypeConverter;
import com.example.myfitnesstracker.model.typeconverter.SensorDataListTypeConverter;

@Database(entities = {SensorData.class, Activity_log.class , MoodData.class},version = 1)
@TypeConverters({SensorDataListTypeConverter.class, ActivityLogDataTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SensorDataDao sensorDataDao();
    public abstract ActivityDataDao activityDataDao();
    public abstract MoodDataDao moodDataDao();
}
