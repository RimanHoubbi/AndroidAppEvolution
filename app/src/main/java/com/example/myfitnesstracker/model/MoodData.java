package com.example.myfitnesstracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mooddata")
public class MoodData {
    @PrimaryKey(autoGenerate = true)
     int uid;
    String satisfiedMeter;
    String calmMeter;
    String happinessMeter;
    String excitedMeter;
    String energyMeter;
    String sleepyMeter;
    String negative_events;
    String positive_events;
    String alone;
    String peopleAroundYou;
    String peopleLikeability;
    String place;
    String satisfiedrate;
    String failurerate;
    String impulsively;
    String aggressive;
    String cancel_reason;
    String notes;
    long moodStartTime;
    long moodSEndTime;


    public MoodData(String satisfiedMeter, String calmMeter, String happinessMeter, String excitedMeter, String energyMeter, String sleepyMeter, String negative_events, String positive_events, String alone, String peopleAroundYou, String peopleLikeability, String place, String satisfiedrate, String failurerate, String impulsively, String aggressive, String cancel_reason, String notes, long moodStartTime, long moodSEndTime) {
        this.satisfiedMeter = satisfiedMeter;
        this.calmMeter = calmMeter;
        this.happinessMeter = happinessMeter;
        this.excitedMeter = excitedMeter;
        this.energyMeter = energyMeter;
        this.sleepyMeter = sleepyMeter;
        this.negative_events = negative_events;
        this.positive_events = positive_events;
        this.alone = alone;
        this.peopleAroundYou = peopleAroundYou;
        this.peopleLikeability = peopleLikeability;
        this.place = place;
        this.satisfiedrate = satisfiedrate;
        this.failurerate = failurerate;
        this.impulsively = impulsively;
        this.aggressive = aggressive;
        this.cancel_reason = cancel_reason;
        this.notes = notes;
        this.moodStartTime = moodStartTime;
        this.moodSEndTime = moodSEndTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSatisfiedMeter() {
        return satisfiedMeter;
    }

    public void setSatisfiedMeter(String satisfiedMeter) {
        this.satisfiedMeter = satisfiedMeter;
    }

    public String getCalmMeter() {
        return calmMeter;
    }

    public void setCalmMeter(String calmMeter) {
        this.calmMeter = calmMeter;
    }

    public String getHappinessMeter() {
        return happinessMeter;
    }

    public void setHappinessMeter(String happinessMeter) {
        this.happinessMeter = happinessMeter;
    }

    public String getExcitedMeter() {
        return excitedMeter;
    }

    public void setExcitedMeter(String excitedMeter) {
        this.excitedMeter = excitedMeter;
    }

    public String getEnergyMeter() {
        return energyMeter;
    }

    public void setEnergyMeter(String energyMeter) {
        this.energyMeter = energyMeter;
    }

    public String getSleepyMeter() {
        return sleepyMeter;
    }

    public void setSleepyMeter(String sleepyMeter) {
        this.sleepyMeter = sleepyMeter;
    }

    public String getNegative_events() {
        return negative_events;
    }

    public void setNegative_events(String negative_events) {
        this.negative_events = negative_events;
    }

    public String getPositive_events() {
        return positive_events;
    }

    public void setPositive_events(String positive_events) {
        this.positive_events = positive_events;
    }

    public String getAlone() {
        return alone;
    }

    public void setAlone(String alone) {
        this.alone = alone;
    }

    public String getPeopleAroundYou() {
        return peopleAroundYou;
    }

    public void setPeopleAroundYou(String peopleAroundYou) {
        this.peopleAroundYou = peopleAroundYou;
    }

    public String getPeopleLikeability() {
        return peopleLikeability;
    }

    public void setPeopleLikeability(String peopleLikeability) {
        this.peopleLikeability = peopleLikeability;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSatisfiedrate() {
        return satisfiedrate;
    }

    public void setSatisfiedrate(String satisfiedrate) {
        this.satisfiedrate = satisfiedrate;
    }

    public String getFailurerate() {
        return failurerate;
    }

    public void setFailurerate(String failurerate) {
        this.failurerate = failurerate;
    }

    public String getImpulsively() {
        return impulsively;
    }

    public void setImpulsively(String impulsively) {
        this.impulsively = impulsively;
    }

    public String getAggressive() {
        return aggressive;
    }

    public void setAggressive(String aggressive) {
        this.aggressive = aggressive;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getMoodStartTime() {
        return moodStartTime;
    }

    public void setMoodStartTime(long moodStartTime) {
        this.moodStartTime = moodStartTime;
    }

    public long getMoodSEndTime() {
        return moodSEndTime;
    }

    public void setMoodSEndTime(long moodSEndTime) {
        this.moodSEndTime = moodSEndTime;
    }
}
