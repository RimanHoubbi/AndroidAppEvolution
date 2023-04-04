package com.example.myfitnesstracker.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ActivityDataDao {

    @Query("SELECT * FROM activity_log")
    List<Activity_log> getAll();

    @Insert
    void insertAll(Activity_log...activity_log);

    @Query("DELETE FROM activity_log WHERE uid = :uniqueID")
    abstract void deleteByUniqueId(long uniqueID);

    @Query("SELECT * FROM activity_log WHERE startTimeMilli>=:startTime AND endTimeMilli<=:endTIme")
    abstract List<Activity_log> getListOfEntriesInTimeFraame(long startTime, long endTIme);

    @Query("SELECT * FROM activity_log WHERE (type=:Type OR type=:Type2) AND startTimeMilli>=:startTime AND endTimeMilli<=:endTIme")
    abstract List<Activity_log> getTypeOfAct(String Type, String Type2, long startTime, long endTIme);


}
