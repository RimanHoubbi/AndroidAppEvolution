package com.example.myfitnesstracker.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity_log")
public class Activity_log {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    String type;
    String date;
    String startTime;
    String endTime;
    Long startTimeMilli;
    Long endTimeMilli;


    public Activity_log(String type, String date, String startTime, String endTime, Long startTimeMilli, Long endTimeMilli) {
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTimeMilli = startTimeMilli;
        this.endTimeMilli = endTimeMilli;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getStartTimeMilli() {
        return startTimeMilli;
    }

    public void setStartTimeMilli(Long startTimeMilli) {
        this.startTimeMilli = startTimeMilli;
    }

    public Long getEndTimeMilli() {
        return endTimeMilli;
    }

    public void setEndTimeMilli(Long endTimeMilli) {
        this.endTimeMilli = endTimeMilli;
    }
}
