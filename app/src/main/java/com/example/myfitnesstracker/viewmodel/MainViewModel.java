package com.example.myfitnesstracker.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.MoodData;

public class MainViewModel extends ViewModel {
    String satisfiedMeter = "0";
    String calmMeter = "0";
    String happinessMeter = "0";
    String excitedMeter = "0";
    String energyMeter = "0";
    String sleepyMeter = "0";
    String negative_events = "0";
    String positive_events= "0";
    String alone= "0";
    String peopleAroundYou= "0";
    String peopleLikeability= "0";
    String place= "0";
    String satisfiedrate= "0";
    String failurerate= "0";
    String impulsively= "0";
    String aggressive= "0";
    String cancel_reason= "0";
    String notes= "0";
    long moodStartTime;
    long moodEndTime;
    AppDatabase db;

    public long getMoodStartTime() {
        return moodStartTime;
    }

    public void setMoodStartTime(long moodStartTime) {
        this.moodStartTime = moodStartTime;
    }

    public long getMoodEndTime() {
        return moodEndTime;
    }

    public void setMoodEndTime(long moodEndTime) {
        this.moodEndTime = moodEndTime;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String noteString) {
        notes = notes +". " + noteString;
    }

    public MoodData saveMoodData(){

        MoodData moodData =
                new MoodData(
                        getSatisfiedMeter(),
                        getCalmMeter(),
                        getHappinessMeter(),
                        getExcitedMeter(),
                        getEnergyMeter(),
                        getSleepyMeter(),
                        getNegative_events(),
                        getPositive_events(),
                        getAlone(),
                        getPeopleAroundYou(),
                        getPeopleLikeability(),
                        getPlace(),
                        getSatisfiedrate(),
                        getFailurerate(),
                        getImpulsively(),
                        getAggressive(),
                        getCancel_reason(),
                        getNotes(),
                        getMoodStartTime(),
                        getMoodEndTime()
                );
        return  moodData;
    }
}
