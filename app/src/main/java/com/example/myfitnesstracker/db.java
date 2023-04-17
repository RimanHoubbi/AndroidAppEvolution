package com.example.myfitnesstracker;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.example.myfitnesstracker.model.ActivityDataDao;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.SensorDataDao;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class db extends AppCompatActivity implements View.OnClickListener{

    Button back;
    Button exportact;
    Button exportmd;
    Button exportsens;
    DBHandler dbHandler;
    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_db);

        dbHandler=new DBHandler(this);

        sqldb = dbHandler.getWritableDatabase();

        exportact = findViewById(R.id.export_act);
        exportact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/appdb";

                File exportDir = new File(path);

                if (!exportDir.exists()){
                    exportDir.mkdirs();
                }

                File file = new File(exportDir, "activity.csv");
                try
                {
                    file.createNewFile();
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    Cursor curCSV = sqldb.rawQuery("SELECT * FROM activity_log",null);
                    csvWrite.writeNext(curCSV.getColumnNames());
                    while(curCSV.moveToNext())
                    {
                        //Which column you want to exprort
                        String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)};
                        csvWrite.writeNext(arrStr);
                    }
                    csvWrite.close();
                    curCSV.close();
                }
                catch(Exception sqlEx)
                {
                    Log.e("Error export activity", sqlEx.getMessage(), sqlEx);
                }
            }
        });

        exportmd = findViewById(R.id.export_mood);
        exportmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/appdb";

                File exportDir = new File(path);

                if (!exportDir.exists()){
                    exportDir.mkdirs();
                }

                File file = new File(exportDir, "mood.csv");
                try
                {
                    file.createNewFile();
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    Cursor curCSV = sqldb.rawQuery("SELECT * FROM mooddata",null);
                    csvWrite.writeNext(curCSV.getColumnNames());
                    while(curCSV.moveToNext())
                    {
                        //Which column you want to exprort
                        String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2), curCSV.getString(3), curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7), curCSV.getString(8), curCSV.getString(9), curCSV.getString(10), curCSV.getString(11), curCSV.getString(12), curCSV.getString(13), curCSV.getString(14), curCSV.getString(15), curCSV.getString(16), curCSV.getString(17), curCSV.getString(18), curCSV.getString(19), curCSV.getString(20)};
                        csvWrite.writeNext(arrStr);
                    }
                    csvWrite.close();
                    curCSV.close();
                }
                catch(Exception sqlEx)
                {
                    Log.e("Error export mood", sqlEx.getMessage(), sqlEx);
                }
            }
        });

        exportsens = findViewById(R.id.export_sensors);
        exportsens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/appdb";

                File exportDir = new File(path);

                if (!exportDir.exists()){
                    exportDir.mkdirs();
                }

                File file = new File(exportDir, "sensor.csv");
                try
                {
                    file.createNewFile();
                    CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                    Cursor curCSV = sqldb.rawQuery("SELECT * FROM sensordata",null);
                    csvWrite.writeNext(curCSV.getColumnNames());
                    while(curCSV.moveToNext())
                    {
                        //Which column you want to exprort
                        String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2), curCSV.getString(3), curCSV.getString(4)};
                        csvWrite.writeNext(arrStr);
                    }
                    csvWrite.close();
                    curCSV.close();
                }
                catch(Exception sqlEx)
                {
                    Log.e("Error export sense", sqlEx.getMessage(), sqlEx);
                }
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(db.this, StatisticsPageActivity.class));
    }
}
