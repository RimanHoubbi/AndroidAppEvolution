package com.example.myfitnesstracker.view.activities;

import android.os.Bundle;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.view.fragments.MySettingsFragment;


public class MySettings extends LocalizationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container_view, new MySettingsFragment())
                .commit();


    }

    public void changeToEnglish(){
        setLanguage("en");
    }
    public  void changeToGerman(){
        setLanguage("de");
    }
}

