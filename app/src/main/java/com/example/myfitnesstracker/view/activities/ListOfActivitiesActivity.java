package com.example.myfitnesstracker.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.appcompat.widget.Toolbar;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.model.ActivityDataDao;
import com.example.myfitnesstracker.model.Activity_log;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.view.adapter.ActivitiesAdapter;

import java.util.List;

public class ListOfActivitiesActivity extends AppCompatActivity {

    AppDatabase db;
    ActivityDataDao activityDataDao;
    List<Activity_log> activityList;
    RecyclerView rv_activity_list;
    ActivitiesAdapter activitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_activities);
        db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Tracker_Database").build();
        activityDataDao =db.activityDataDao();
        rv_activity_list = findViewById(R.id.rv_list_of_activities);
        rv_activity_list.setLayoutManager(new LinearLayoutManager(this));
        
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar3);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               activityList=activityDataDao.getAll();
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       activitiesAdapter = new ActivitiesAdapter(activityList, new ActivitiesAdapter.OnItemClickListener() {
                           @Override
                           public void onItemClick(Activity_log activityLog) {
                               Runnable runnable1 = new Runnable() {
                                   @Override
                                   public void run() {
                                       activityDataDao.deleteByUniqueId(activityLog.getUid());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                   }
                               };
                               new Thread(runnable1).start();
                           }
                       });
                       rv_activity_list.setAdapter(activitiesAdapter);
                   }
               });
            }
        };
        new Thread(runnable).start();


    }
}
