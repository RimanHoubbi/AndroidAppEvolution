package com.example.myfitnesstracker;

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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StatisticsPageActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialize Variables
    BarChart barChartActivity;
    Button button7days;
    Button button30days;
    Button button90days;

    AppDatabase db2;
    ActivityDataDao activityDataDao;
    MoodDataDao moodDataDao;
    Spinner spinneract;
    Spinner spinnermood;
    String type_de;
    String type_eng;
    String type;
    ArrayList<Float> dbEntries;
    int days;
    LineChart lineChartMood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        spinneract = findViewById(R.id.spinner4);


        // Assign Variables
        lineChartMood = findViewById(R.id.line_chart_mood);
        barChartActivity = findViewById(R.id.bar_chart_activity);
        button7days = findViewById(R.id.button_7_days);
        button30days = findViewById(R.id.button_30_days);
        button90days = findViewById(R.id.button_90_days);

        type_de="alles";
        type_eng="all";
        type="all";
        days=7;

        button7days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=7;
                makeBarChart(days, type_eng, type_de);
                makeLineChart(days, type);
            }
        });
        button30days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days=30;
                makeBarChart(days, type_eng, type_de);
                makeLineChart(days, type);
            }
        });
        button90days.setOnClickListener(this);

        db2= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Tracker_Database").allowMainThreadQueries().build();
        activityDataDao = db2.activityDataDao();
        moodDataDao = db2.moodDataDao();

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
        spinneract.setAdapter(arrayAdapter);

        spinneract.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spinnermood = findViewById(R.id.spinner6);

        ArrayList<String> arrayListMood = new ArrayList<>();
        arrayListMood.add("Alles");
        arrayListMood.add("Zufrieden");
        arrayListMood.add("Ruhig");
        arrayListMood.add("Wohl");
        arrayListMood.add("Entspannt");
        arrayListMood.add("Energieladen");
        arrayListMood.add("Wach");
        ArrayAdapter<String> arrayAdaptermod = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListMood);
        arrayAdaptermod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermood.setAdapter(arrayAdaptermod);

        spinnermood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = parent.getItemAtPosition(position).toString();
                if (pos.equals("Alles")){
                    type="all";
                    makeLineChart(days, type);
                }else if(pos.equals("Zufrieden")){
                    type="satisfied";
                    makeLineChart(days, type);
                }else if(pos.equals("Ruhig")){
                    type="calm";
                    makeLineChart(days, type);
                }else if(pos.equals("Wohl")){
                    type="happines";
                    makeLineChart(days, type);
                }else if(pos.equals("Entspannt")){
                    type="excited";
                    makeLineChart(days, type);
                }else if(pos.equals("Energie")){
                    type="energy";
                    makeLineChart(days, type);
                }else if(pos.equals("Wach")){
                    type="sleepy";
                    makeLineChart(days, type);
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                type="all";
            }
        });

        makeBarChart(7, type_eng, type_de);
        makeLineChart(days, type);

    }

    @Override
    public void onClick(View v)
    {
        days=90;
        makeBarChart(days, type_eng, type_de);
        makeLineChart(days, type);
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
        ArrayList<String> Dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        String dateold = calendar.getTime().toString();
        int count=0;
        Dates.add(count, dateold);
        for (int i=1; i<=days; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, -days);
            String date = calendar.getTime().toString();
            Dates.add(count, date);
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
    private void makeLineChart(int daysShown, String type){

        ArrayList<ArrayList<Entry>> linesMoodEntries = new ArrayList<>(); // List of all Moods
        ArrayList<Entry> Mood1 = new ArrayList<>();
        ArrayList<Entry> Mood2 = new ArrayList<>();
        ArrayList<Entry> Mood3 = new ArrayList<>();
        ArrayList<Entry> Mood4 = new ArrayList<>();
        ArrayList<Entry> Mood5 = new ArrayList<>();
        ArrayList<Entry> Mood6 = new ArrayList<>();
        linesMoodEntries.add(Mood1);
        linesMoodEntries.add(Mood2);
        linesMoodEntries.add(Mood3);
        linesMoodEntries.add(Mood4);
        linesMoodEntries.add(Mood5);
        linesMoodEntries.add(Mood6);

        //Set Graphs Axis
        YAxis yAxisL = lineChartMood.getAxisLeft();
        YAxis yAxisR = lineChartMood.getAxisRight();
        yAxisL.setAxisMinimum(0);
        yAxisL.setAxisMaximum(100);
        yAxisR.setAxisMinimum(0);
        yAxisR.setAxisMaximum(100);
        XAxis xAxis = lineChartMood.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(daysShown-1);

        //get database data
        ArrayList<ArrayList<Float>> dbEntries = getMoodScoresFromDB(daysShown);

        // Set chart values
        for(int i = 0; i < linesMoodEntries.size(); i++){
            ArrayList<Float> mood = dbEntries.get(i);
            for(int j = 0; j < daysShown; j++){
                float value = mood.get(j);
                if(value != 404){ // checking if there is valid data
                    Entry lineEntry = new Entry(j, value);// Initialize Entry
                    linesMoodEntries.get(i).add(lineEntry);// Add Values in Array List
                }
            }
        }

        //making the 6 different lines
        String color1 = "#4361ee";
        LineDataSet line1 = new LineDataSet(Mood1, getResources().getString(R.string._satisfied));
        line1.setColor(Color.parseColor(color1));
        line1.setCircleColor(Color.parseColor(color1));
        line1.setDrawValues(false);
        line1.setLineWidth(2F);
        String color2 = "#3a0ca3";
        LineDataSet line2 = new LineDataSet(Mood2, getResources().getString(R.string._calm));
        line2.setColor(Color.parseColor(color2));
        line2.setCircleColor(Color.parseColor(color2));
        line2.setDrawValues(false);
        line2.setLineWidth(2F);
        String color3 = "#972c8b";
        LineDataSet line3 = new LineDataSet(Mood3, getResources().getString(R.string._well));
        line3.setColor(Color.parseColor(color3));
        line3.setCircleColor(Color.parseColor(color3));
        line3.setDrawValues(false);
        line3.setLineWidth(2F);
        String color4 = "#e63367";
        LineDataSet line4 = new LineDataSet(Mood4, getResources().getString(R.string._relaxed));
        line4.setColor(Color.parseColor(color4));
        line4.setCircleColor(Color.parseColor(color4));
        line4.setDrawValues(false);
        line4.setLineWidth(2F);
        String color5 = "#fb7607";
        LineDataSet line5 = new LineDataSet(Mood5, getResources().getString(R.string._full_energy));
        line5.setColor(Color.parseColor(color5));
        line5.setCircleColor(Color.parseColor(color5));
        line5.setDrawValues(false);
        line5.setLineWidth(2F);
        String color6 = "#ffbe0b";
        LineDataSet line6 = new LineDataSet(Mood6, getResources().getString(R.string._awake));
        line6.setColor(Color.parseColor(color6));
        line6.setCircleColor(Color.parseColor(color6));
        line6.setDrawValues(false);
        line6.setLineWidth(2F);

        //adding all lines to the list: lineDataSetList
        ArrayList<ILineDataSet> lineDataSetList = new ArrayList<>(); // List of the sets
        if (type.equals("all")){
            lineDataSetList.add(line1);
            lineDataSetList.add(line2);
            lineDataSetList.add(line3);
            lineDataSetList.add(line4);
            lineDataSetList.add(line5);
            lineDataSetList.add(line6);
        }else if (type.equals("satisfied")){
            lineDataSetList.add(line1);
        }else if (type.equals("calm")){
            lineDataSetList.add(line2);
        }else if (type.equals("happines")){
            lineDataSetList.add(line3);
        }else if (type.equals("excited")){
            lineDataSetList.add(line4);
        }else if (type.equals("energy")){
            lineDataSetList.add(line5);
        }else if (type.equals("sleepy")){
            lineDataSetList.add(line6);
        }

        //Inputting the line data into the graph
        LineData data = new LineData(lineDataSetList);
        lineChartMood.setData(data);
        lineChartMood.animateX(3000);
        lineChartMood.getDescription().setText(" ");

    }

    private ArrayList<ArrayList<Float>> getMoodScoresFromDB(int daysToShow){
        ArrayList<ArrayList<Float>> dbEntries= new ArrayList<>();//List where the entries of the DB are put into
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        long timeStartOfTheDay = now.getTime() - (now.getTime() % millisecondsPerDay); //gets the time of the first millisecond of the current day

        //initialize array for dbEntries
        for(int p = 0;p<6;p++){
            ArrayList<Float> entries = new ArrayList<>();
            dbEntries.add(entries);
        }

        //gets the DB entry for every day, starting with the day furthest in the past
        for(int i = daysToShow-1; i >= 0; i--){
            long neededDay = timeStartOfTheDay - (i * millisecondsPerDay);
            ArrayList<Float> entriesOneDay = getMoodData(neededDay, neededDay + millisecondsPerDay); //get the DB entries for every mood for the needed day
            for(int p = 0; p < 6 ; p++){
                dbEntries.get(p).add(entriesOneDay.get(p));
            }
        }

        return  dbEntries;
    }

    /**
     * makes example entries in the DB for the charts
     */

    public ArrayList<Float> getMoodData(long minTime, long maxTime){

        //list with the scores with [0] = zufrieden, [1] = ruhe, [2] = wohl, [3] = entspannt, [4] = energie, [5] = wach
        ArrayList<Float> moodsScores = new ArrayList<>();
        ArrayList<Integer> moodsCounters = new ArrayList<>();

       /* for(int i = 0; i < 6; i++){
            moodsScores.add((float)404);//impossible value to test if there is any data at all (404 error not found)
            moodsCounters.add(0);//counts how many entries exists
        }*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<MoodData> tempList = (ArrayList<MoodData>) moodDataDao.getMoodDataInTimeFrame(minTime, maxTime);
                if (!tempList.isEmpty()){

                    /*
                    Write code for averaging here
                    * */
                    moodsScores.add(Float.parseFloat(tempList.get(0).getSatisfiedMeter()));
                    moodsScores.add(Float.parseFloat(tempList.get(0).getCalmMeter()));
                    moodsScores.add(Float.parseFloat(tempList.get(0).getHappinessMeter()));
                    moodsScores.add(Float.parseFloat(tempList.get(0).getExcitedMeter()));
                    moodsScores.add(Float.parseFloat(tempList.get(0).getEnergyMeter()));
                    moodsScores.add(Float.parseFloat(tempList.get(0).getSleepyMeter()));
                }else{
                    moodsScores.add(0F);
                    moodsScores.add(0F);
                    moodsScores.add(0F);
                    moodsScores.add(0F);
                    moodsScores.add(0F);
                    moodsScores.add(0F);
                }


            }

        }).run();



        return moodsScores;
    }

    /**
     * @param daysShown number of days which need to be shown on the chart
     */
}