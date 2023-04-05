package com.example.myfitnesstracker.view.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.StatisticsPageActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.Random;

public class MainActivity extends LocalizationActivity {

    public static final String PLANT_PLACES_PREFS = "PLANT_PLACES_PREFS";
    public static final String TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Bundle savedInstanceState;
    LinearLayout activitiesListActivity;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activitiesListActivity = findViewById(R.id.activities_list_activity);
        // connect floating button to the settings page
        FloatingActionButton mySettings = (FloatingActionButton) findViewById(R.id.my_settings);
        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MySettings.class));
            }
        });

        //generate a unique user ID when the app is first opened
        SharedPreferences userData = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userID = userData.getString("userID", "").toString();
        if(userID == ""){
            Date now = new Date();
            Random random = new Random();
            int addOn = random.nextInt(9000000) + 1000000;
            SharedPreferences.Editor editor = userData.edit();
            editor.putString("userID", ""+now.getTime()+ addOn);// generated userId as String (20 characters long)
            editor.apply();
        }

        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.getString("lang", "de").equals("en")) {
            setLanguage("en");
        } else {
            setLanguage("de");
        }

        isLocationPermissionGranted();
        //access last location after permission
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                  //  Toast.makeText(MainActivity.this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        activitiesListActivity.setOnClickListener(view -> {
            startActivity(new Intent(this,ListOfActivitiesActivity.class));
        });

        /** Called when the user taps the Log Activity card */

        CardView cardView = findViewById(R.id.activityCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivitiesPageActivity.class);
                startActivity(intent);
            }
        });

        /** Called when the user taps the Log Mood card */
        CardView cardView2 = findViewById(R.id.moodCard);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity0.class);
                startActivity(intent);
            }
        });

        /** Called when the user taps the My Statistics card */
        CardView cardView3 = findViewById(R.id.statisticsCard);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsPageActivity.class);
                startActivity(intent);
            }
        });
    }

    //ask for permission
    private boolean isLocationPermissionGranted() {
        boolean result = false;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 55
            );
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    //Tagesabfrage
    public void displayAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((getResources().getString(R.string._daily_query)));
        builder.setMessage(getResources().getString(R.string._daily_question_query));
        final SeekBar seek = new SeekBar(this);
        seek.setMax(10);
        seek.setKeyProgressIncrement(1);
        builder.setView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekBarValue = seek.getProgress();
                if (seekBarValue == 1) {
                    //fragbatterie um 20 uhr zeigen
                } else {
                    //wähle zeitraum wann fragebatterie gezeigt werden soll
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setNegativeButton("Fertig", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
        //Create AlertDialog object
        AlertDialog dialog = builder.create();
        //show the AlertDialog using show() method
        dialog.show();

    }

    public class TermsAndConditionsDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(Html.fromHtml("By using this app, you agree to our <a href=\"https://www.app-privacy-policy.com/live.php?token=X5oaGp9KJ0Idt4w5IfrPNQfHceIdBTOH\">Terms and Conditions</a> and <a href=\"https://www.app-privacy-policy.com/live.php?token=Ky6BZHHcnBWJi6MYFmCSIbPbnyXYxMqC\">Privacy Policy</a>"))
                    .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //make note that the user agree
                            SharedPreferences prefs = MainActivity.this.getSharedPreferences(PLANT_PLACES_PREFS, Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putBoolean(TERMS_AND_CONDITIONS, true);
                            edit.commit();
                        }
                    });
            return builder.create();
        }

    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= getSharedPreferences(PLANT_PLACES_PREFS, Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(TERMS_AND_CONDITIONS, false)) {
        MainActivity.TermsAndConditionsDialogFragment tsandcs = new MainActivity.TermsAndConditionsDialogFragment();
        Dialog dialog = tsandcs.onCreateDialog(savedInstanceState);
        dialog.show();
        ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}