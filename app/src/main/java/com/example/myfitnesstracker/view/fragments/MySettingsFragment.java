package com.example.myfitnesstracker.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.CheckBoxPreference;
import android.widget.Toast;

import com.akexorcist.localizationactivity.core.OnLocaleChangedListener;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.view.activities.MySettings;
import com.example.myfitnesstracker.view.activities.QueryActivity;

public class MySettingsFragment extends PreferenceFragmentCompat implements OnLocaleChangedListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        ListPreference languagePreferences = findPreference("lang");
        languagePreferences.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String code = "";
                if (newValue.toString().equals("English")){
                    editor.putString("lang","en");
                    ((MySettings)getActivity()).changeToEnglish();
                }else{
                    editor.putString("lang","de");
                    ((MySettings)getActivity()).changeToGerman();
                }
                editor.apply();

                return false;
            }
        });
        
        Preference query = findPreference("query");
        Intent intent = new Intent(getContext(), QueryActivity.class);
        query.setIntent(intent);
        
         // save checkbox values for enabling app notifications if check box is checked in settings
        CheckBoxPreference checkBox = findPreference("notification");
        checkBox.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                if (checkBox.isChecked()) {
                    //checkBox.setEnabled(true);
                    editor.putBoolean("Notification", true);
                    Toast.makeText(getContext(), "Notifications enabled", Toast.LENGTH_LONG).show();
                }
                else {
                    //checkBox.setEnabled(false);
                    editor.putBoolean("Notification", false);
                }
                editor.apply();
                return false;
            }
        });
    }

    @Override
    public void onAfterLocaleChanged() {

    }

    @Override
    public void onBeforeLocaleChanged() {

    }
}
