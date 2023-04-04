package com.example.myfitnesstracker.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.DBHandler;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.model.ActivityDataDao;
import com.example.myfitnesstracker.model.Activity_log;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.SensorData;
import com.example.myfitnesstracker.model.SensorDataDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class ActivitiesPageActivity extends LocalizationActivity implements SensorEventListener,OnClickListener {
    private SensorManager sensorManager;
    private Sensor Accelerometer;
    Handler handler;
    int interval = 6000; //read sensor data every 1 minute = 6000 ms
    boolean flag = false; //initialized
    TextView txt_x; //declare x axis object
    TextView txt_y; //declare y axis object
    TextView txt_z; //declare z axis object
    TextView tv_bpm;
    TextView tv_Heart;
    Random rand = new Random();
    int multiplier =50;
    int upperbound = 15;
    int randomInitialHeartbeat = (rand.nextInt(upperbound)+63)*multiplier;
    int initialHeartValueForCheck = randomInitialHeartbeat;
    double previous_x =0;
    double previous_y =0;
    double previous_z =0;
    Button button;
    DBHandler dbHandler;
    String startTime;
    Long startTimeMilli;
    Long endTimeMilli;
    String endTime;
    String currentDate;


    private double accelerationCurrentValue;
    private double accelerationPreviousValue;
    private double changedAcceleration;

    ArrayList<SensorData> periodicSensorData = new ArrayList<>();

    Button startButton;
    Button stopButton;
    Spinner spinner;
    AppDatabase db;



    SensorDataDao sensorDataDao;
    ActivityDataDao activityDataDao;

    private final Runnable processSensors = new Runnable() {
        @Override
        public void run() {
            // Do work with the sensor values.

            //flag = true;
            // The Runnable is posted to be run after the specified amount of time elapses
            handler.postDelayed(this, interval);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_page);
        db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Tracker_Database").build();
        sensorDataDao=db.sensorDataDao();
        activityDataDao =db.activityDataDao();

        dbHandler=new DBHandler(this);
        
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.buttonactivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToRecordFinishedActivity();
            }
        });


        spinner = findViewById(R.id.spinner_activities);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.listActivities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        tv_bpm=findViewById(R.id.tv_bpm);
        //tv_Heart=findViewById(R.id.tv_heart);
        startButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        handler = new Handler();

        //txt_x = findViewById(R.id.textView2); //create x axis object
        //txt_y = findViewById(R.id.textView3); // create y axis object
        //txt_z = findViewById(R.id.textView4); //create z axis object


        //create the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //create the accelerometer
        Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.getString("lang","de").equals("en")){
            setLanguage("en");
        }else{
            setLanguage("de");
        }
    }


    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        handler.post(processSensors); //puts the Runnable in a message queue
    }

    protected void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(this);
        handler.removeCallbacks(processSensors); // Remove any pending posts of Runnable that are in the message queue.
    }
    Boolean isFirstTime=true;
    Timer timer=   new Timer();
    Timer timer2 = new Timer();


    public void onSensorChanged(SensorEvent sensorEvent) {

        //assign directions
        if (flag) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];



            accelerationCurrentValue = Math.sqrt(x * x + y * y + z * z);

            double changedAcceleration = Math.abs(accelerationCurrentValue - accelerationPreviousValue);
            accelerationPreviousValue = accelerationCurrentValue;

         /*   if (x!=previous_x || y!=previous_y || z!=previous_z){
                if(randomInitialHeartbeat<initialHeartValueForCheck+(150*multiplier)){
                    Log.d("Ahmad", "onSensorChanged: "+ (++ randomInitialHeartbeat)/multiplier);
                }

            }else{

                if (randomInitialHeartbeat>initialHeartValueForCheck){

                    randomInitialHeartbeat=randomInitialHeartbeat-2;
                }
                tv_bpm.setText(""+ randomInitialHeartbeat/multiplier+" bpm");

            } */

            //txt_x.setText(String.format("Current =%s", accelerationCurrentValue));
            //txt_y.setText(String.format("prev =%s", accelerationPreviousValue));
            //txt_z.setText(String.format("changed =%s", changedAcceleration));

            if (isFirstTime){
                int begin=0;
                int timeInterval =100;
                timer.schedule(new TimerTask() {
                    final int counter=0;
                    @Override
                    public void run() {
                        sensorDataDao.insertAll(new SensorData(
                                System.currentTimeMillis(),
                                sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2], getResources().getStringArray(R.array.listActivities)[spinner.getSelectedItemPosition()]
                        ));




                    }
                },begin,2000);
                isFirstTime=false;
            }

            previous_x =x;
            previous_y= y;
            previous_z= z;

        }else{
            //txt_x.setText("Waiting for sensor data..");
            //txt_y.setText("Waiting for sensor data..");
            //txt_z.setText("Waiting for sensor data..");

        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        //empty for now

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                sensorDataDao=db.sensorDataDao();
                activityDataDao =db.activityDataDao();

                dbHandler=new DBHandler(this);
                timer= new Timer();
                timer2= new Timer();
                isFirstTime = true;
               // tv_bpm.setVisibility(View.VISIBLE);
               // tv_Heart.setVisibility(View.VISIBLE);
                startTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                startTimeMilli = System.currentTimeMillis();
                currentDate = new SimpleDateFormat("MMM d yyyy",Locale.getDefault()).format(new Date());
                sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                flag=true;
                dialogTimer.start();

                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                sensorDataDao.getAll();
                                periodicSensorData.clear();

                            }
                        });
                    }
                },0,60000);


                break;
            case R.id.stop:
                sensorManager.unregisterListener(this, Accelerometer);
                sensorManager.unregisterListener(this);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                endTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                endTimeMilli= System.currentTimeMillis();

                exitActivity();
                //Borg skala
                showDialogSpinner();
                //goToMainActivity0(); //fragebatterie nach dem sport beantworten
                break;
        }
    }
    
     /*Ask user after three hours if they are done with sport
    if done >> exit the activity, save to database and display the questionnaire
    if not >> dismiss the dialog
    */ 
    
    void exitActivity() {
        sensorManager.unregisterListener(this, Accelerometer);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        endTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                activityDataDao.insertAll(new Activity_log(
                        getResources().getStringArray(R.array.listActivities)[spinner.getSelectedItemPosition()],
                        currentDate,
                        startTime,
                        endTime,
                        startTimeMilli,
                        endTimeMilli
                ));
            }
        };

        new Thread(runnable).start();
        timer.cancel();
        timer2.cancel();
        flag=false;
        dbHandler.saveData();
    }
    
    void goToMainActivity0() {
        Intent intent = new Intent(this, MainActivity0.class);
        startActivity(intent);
    }
    
    void goToRecordFinishedActivity() {
        Intent intent = new Intent(this, RecordFinishedActivity.class);
        startActivity(intent);
    }

void showDialogSpinner() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
        mBuilder.setTitle("Wie anstrengend war das Training?");
        final Spinner dialogSpinner = (Spinner) mView.findViewById(R.id.dialogSpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item,
                 getResources().getStringArray(R.array.DialogSpinner));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialogSpinner.setAdapter(adapter2);
        mBuilder.setPositiveButton("Absenden", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //send data to database
                //exitActivity();
                goToMainActivity0();
            }
        });

        //Create AlertDialog object
        mBuilder.setView(mView);
        //show the AlertDialog using show() method
        AlertDialog alertDialog2 = mBuilder.create();
        alertDialog2.show();
    }
    
    void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erinnerung");
        builder.setMessage("Schon fertig? Falls ja, klicke auf Weiter, um die tatsächliche Trainingsdauer anzugeben");
        //add action buttons
        builder.setPositiveButton("Weiter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //exitActivity();
               // goToMainActivity0(); //fragebatterie
                  goToRecordFinishedActivity();
            }
        });
        builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
        //Create AlertDialog object
        AlertDialog alertDialog = builder.create();
        //show the AlertDialog using show() method
        alertDialog.show();

    }

    // 10800000ms = 3 hours

    final CountDownTimer dialogTimer = new CountDownTimer(120000, 1000) {
        public void onTick(long millisUntilFinished) {
        }
        public void onFinish () {
            if (flag == true) {
                showAlertDialog();
            }
        }

    };

}
