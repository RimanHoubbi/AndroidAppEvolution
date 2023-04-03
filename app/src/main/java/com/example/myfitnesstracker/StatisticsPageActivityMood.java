package com.example.myfitnesstracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class StatisticsPageActivityMood extends AppCompatActivity implements View.OnClickListener{

    // Initialize Variables
    LineChart lineChartMood;
    Button button7days;
    Button button30days;
    Button button90days;
    Button button365days;
    Button btn_act;
    DBHandler db;
    AppDatabase db2;
    MoodDataDao moodDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page_mood);

        // Assign Variabl
        lineChartMood = findViewById(R.id.line_chart_mood);
        button7days = findViewById(R.id.button_7_days);
        button30days = findViewById(R.id.button_30_days);
       // button90days = findViewById(R.id.button_90_days);
       // button365days = findViewById(R.id.button_365_days);
        button7days.setOnClickListener(this);
        button30days.setOnClickListener(this);
        button90days.setOnClickListener(this);
        button365days.setOnClickListener(this);
        db = new DBHandler(this);
        db2= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Tracker_Database").allowMainThreadQueries().build();
        moodDataDao = db2.moodDataDao();

        makeLineChart(7);
        makeExampleDbEntries(7);
        btn_act = findViewById(R.id.btn_act);
        btn_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsPageActivityMood.this, StatisticsPageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        int days = 7;
        //if(v.getId() == R.id.button_7_days){days = 7;}
        if (v.getId() == R.id.button_30_days){days = 30;}
        //else if (v.getId() == R.id.button_90_days){days = 90;}
        //else if (v.getId() == R.id.button_365_days){days = 365;}
        makeLineChart(days);
    }

    /**
     * @param daysShown number of days which need to be shown on the chart
     */

    /**
     * @param daysToShow number of days which need to be shown on the chart
     * @return ArrayList<Float> with the minutes of activity for each day as floats
     */
     /**
     * @param daysShown number of days which need to be shown on the chart
     */
    private void makeLineChart(int daysShown){

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
        lineDataSetList.add(line1);
        lineDataSetList.add(line2);
        lineDataSetList.add(line3);
        lineDataSetList.add(line4);
        lineDataSetList.add(line5);
        lineDataSetList.add(line6);

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

        /*

         new Thread(new Runnable() {
            @Override
            public void run() {


            }

        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


         */



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


}