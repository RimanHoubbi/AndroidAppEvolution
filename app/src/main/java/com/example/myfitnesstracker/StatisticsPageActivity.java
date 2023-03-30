package com.example.myfitnesstracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myfitnesstracker.model.ActivityDataDao;
import com.example.myfitnesstracker.model.Activity_log;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.MoodData;
import com.example.myfitnesstracker.model.MoodDataDao;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Date;

public class StatisticsPageActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialize Variables
    BarChart barChartActivity;
    Button button7days;
    Button button30days;
    Button button90days;
    Button button365days;
    Button btn_mood;
    DBHandler db;
    AppDatabase db2;
    ActivityDataDao activityDataDao;
    Spinner spinner;
    String type_de;
    String type_eng;
    ArrayList<Float> dbEntries;
    int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        spinner = findViewById(R.id.spinner4);

        // Assign Variables
        barChartActivity = findViewById(R.id.bar_chart_activity);
        button7days = findViewById(R.id.button_7_days);
        button30days = findViewById(R.id.button_30_days);
        button90days = findViewById(R.id.button_90_days);
        button365days = findViewById(R.id.button_365_days);
        type_de="alles";
        type_eng="all";
        days=7;

        button7days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=7;
                makeBarChart(days, type_eng, type_de);
            }
        });
        button30days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=30;
                makeBarChart(days, type_eng, type_de);
            }
        });
        button90days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=90;
                makeBarChart(days, type_eng, type_de);
            }
        });
        button365days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=365;
                makeBarChart(days, type_eng, type_de);
            }
        });




        db = new DBHandler(this);
        db2= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Tracker_Database").allowMainThreadQueries().build();
        activityDataDao = db2.activityDataDao();



        btn_mood = findViewById(R.id.btn_mood);
        btn_mood.setOnClickListener(this);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Alles");
        arrayList.add("Gehen");
        arrayList.add("Laufen");
        arrayList.add("Schwimmen");
        arrayList.add("Ballsport");
        arrayList.add("Tanzen");
        arrayList.add("Kampfsport");
        arrayList.add("Kraft Training");
        arrayList.add("Yoga");
        arrayList.add("Anderes");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = parent.getItemAtPosition(position).toString();
                if (pos.equals("Alles")){
                    type_de="alles";
                    type_eng="all";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Gehen")){
                    type_de="Gehen";
                    type_eng="Walking";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Laufen")){
                    type_de="Laufen";
                    type_eng="Running";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Schwimmen")){
                    type_de="Schwimmen";
                    type_eng="Swimming";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Ballsport")){
                    type_de="Ballsport";
                    type_eng="Ballsports";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Tanzen")){
                    type_de="Tanzen";
                    type_eng="Dancing";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Kampfsport")){
                    type_de="Kampfsport";
                    type_eng="Martial Arts";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Kraft Training")){
                    type_de="Kraft Training";
                    type_eng="Weight Training";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Yoga")){
                    type_de="Yoga";
                    type_eng="Yoga";
                    makeBarChart(days, type_eng, type_de);
                }else if(pos.equals("Anderes")){
                    type_de="Anderes";
                    type_eng="Other";
                    makeBarChart(days, type_eng, type_de);
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                type_de="alles";
                type_eng="all";
            }
        });
        makeBarChart(7, type_eng, type_de);
        makeExampleDbEntries(7);

    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(StatisticsPageActivity.this, StatisticsPageActivityMood.class);
        startActivity(intent);
    }




    /**
     * @param daysShown number of days which need to be shown on the chart
     */
    private void makeBarChart(int daysShown, String type_eng, String type_de){
        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();// Initialize Array List
        if (type_eng.equals("all"))  {
            dbEntries = getMinutesOfActivityFromDB(daysShown); //List where the entries of the DB are put into
        }else{
            dbEntries = getMinutesOfActivityFromDBACT(daysShown, type_eng, type_de); //List where the entries of the DB are put into
        }



        // Chart Instances
        for (int i=0; i<daysShown; i++){
            float value = dbEntries.get(i);
            BarEntry barEntry = new BarEntry(i, value);// Initialize Entry
            barActivityEntries.add(barEntry);// Add Values in Array List
        }

        XAxis xAxis = barChartActivity.getXAxis();
        xAxis.setDrawGridLines(false);

        BarDataSet barDataSet = new BarDataSet(barActivityEntries, getResources().getString(R.string.chart_active_minutes));// Initialize Bar Data Set
        barDataSet.setColors(Color.parseColor("#4cc9f0"));// Set Bar Color
        barChartActivity.setData(new BarData(barDataSet));// Set Bar Data
        barChartActivity.animateY(3000);// Set Animations
        barChartActivity.getDescription().setText(" ");// Removing Description text
        //barChartActivity.getDescription().setTextColor(Color.WHITE);
        YAxis yAxisL = barChartActivity.getAxisLeft();
        YAxis yAxisR = barChartActivity.getAxisRight();
        yAxisL.setAxisMinimum(0);
        yAxisR.setAxisMinimum(0);
    }

    /**
     * @param daysToShow number of days which need to be shown on the chart
     * @return ArrayList<Float> with the minutes of activity for each day as floats
     */
    private ArrayList<Float> getMinutesOfActivityFromDB(int daysToShow){
        ArrayList<Float> dbEntries= new ArrayList<>();//List where the entries of the DB are put into
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        long timeStartOfTheDay = now.getTime() - (now.getTime() % millisecondsPerDay); //gets the time of the first millisecond of the current day


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = daysToShow-1; i >= 0; i--){
                    long neededDay = timeStartOfTheDay - (i * millisecondsPerDay);
                    //Long time = db.getActivityData(neededDay, neededDay + millisecondsPerDay); //get the DB entries for the needed day
                    ArrayList<Activity_log> tempList = (ArrayList<Activity_log>) activityDataDao.getListOfEntriesInTimeFraame(neededDay, neededDay + millisecondsPerDay);
                    long time = 0;
                    for (int k =0;k<=tempList.size()-1;k++){
                        time= time + (tempList.get(k).getEndTimeMilli()- tempList.get(k).getStartTimeMilli());
                    }
                    dbEntries.add(((float)time)/60000); // calculating milliseconds into minutes
                    Log.d("Ahmad", "run: "+time);
                }

            }

        }).run();

        //gets the DB entry for every day, starting with the day furthest in the past


        return dbEntries;
    }

    private ArrayList<Float> getMinutesOfActivityFromDBACT(int daysToShow, String type_eng, String type_de){
        ArrayList<Float> dbEntries= new ArrayList<>();//List where the entries of the DB are put into
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        long timeStartOfTheDay = now.getTime() - (now.getTime() % millisecondsPerDay); //gets the time of the first millisecond of the current day


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = daysToShow-1; i >= 0; i--){
                    long neededDay = timeStartOfTheDay - (i * millisecondsPerDay);
                    //Long time = db.getActivityData(neededDay, neededDay + millisecondsPerDay); //get the DB entries for the needed day
                    ArrayList<Activity_log> tempList = (ArrayList<Activity_log>) activityDataDao.getTypeOfAct(type_eng, type_de, neededDay, neededDay + millisecondsPerDay);
                    long time = 0;
                    for (int k =0;k<=tempList.size()-1;k++){
                        time= time + (tempList.get(k).getEndTimeMilli()- tempList.get(k).getStartTimeMilli());
                    }
                    dbEntries.add(((float)time)/60000); // calculating milliseconds into minutes
                    Log.d("Ahmad", "run: "+time);
                }

            }

        }).run();

        //gets the DB entry for every day, starting with the day furthest in the past


        return dbEntries;
    }

    /**
     * @param daysShown number of days which need to be shown on the chart
     */

    /**
     * makes example entries in the DB for the charts
     */
    private void makeExampleDbEntries(int daysShown){
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        db.insertActivity("Walking", now.getTime(), now.getTime() + 2700000);
        db.insertActivity("Walking", now.getTime()-(millisecondsPerDay*2), now.getTime()-(millisecondsPerDay*2) + 1800000);
        db.insertActivity("Walking", now.getTime()-(millisecondsPerDay*(daysShown-2)), now.getTime()-(millisecondsPerDay*(daysShown-2)) + 3600000);

        db.safeMoodData(now.getTime(), 10,20,30,40,50,60);
        db.safeMoodData(now.getTime()-(millisecondsPerDay*2), 20,30,40,50,60,70);
        db.safeMoodData(now.getTime()-(millisecondsPerDay*(daysShown-2)), 30,40,50,60,70,80);
    }


}