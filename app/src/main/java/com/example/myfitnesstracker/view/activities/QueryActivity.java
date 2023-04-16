package com.example.myfitnesstracker.view.activities;

import android.animation.LayoutTransition;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.databinding.ActivityQueryBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class QueryActivity extends LocalizationActivity {



private ActivityQueryBinding binding;
private int addButtonClicked = 0; // default value
private MaterialTimePicker picker;
Calendar calendar;
AlarmManager alarmManager;
PendingIntent pendingIntent;
Spinner spinner;
LinearLayout layout1;
TextView backCard;
LinearLayout layout2;
TextView backCard2;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);







        binding = ActivityQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();



        //Enable Animation for the text cards
       layout1 = findViewById(R.id.layoutCard1);
       backCard = findViewById(R.id.back_card1);
       layout1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
       layout2 = findViewById(R.id.layoutCard2);
       backCard2 = findViewById(R.id.back_card2);
       layout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        calendar = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        };

        binding.SetReminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                setAlarm();
                String alarm = setAlarm();






                if (addButtonClicked == 0){

                    for (int i = 1; i <= 10; i++) {

                        int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
                        TextView textView = findViewById(textViewId);

                        if (textView.getText().toString().equals("Not set")) {

                            addButtonClicked = i;
                            break;

                        }
                    }





                    if (alarm != null) {
                        boolean isAlarmAlreadySet = false;


                    // boolean isAlarmWithin30Min = false;
                    for (int i = 1; i <= 10; i++) {
                        isAlarmAlreadySet = false;
                        int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
                        TextView textView = findViewById(textViewId);
                        if (textView.getText().toString().equals(alarm)) {
                            isAlarmAlreadySet = true;
                            break;
                        }
                        if (textView.getText().toString().equals("Not set")) {
                            textView.setText(alarm);
                            isAlarmAlreadySet = true;
                            break;
                        }
                    }
                    if (!isAlarmAlreadySet) {
                        Toast.makeText(QueryActivity.this, "Alarm already set", Toast.LENGTH_SHORT).show();
                    }
                        // Get references to the Cancel/Add buttons
                        String cancelButtonId = "CancelReminderbtn" + addButtonClicked;
                        String addButtonId = "AddReminderbtn" + addButtonClicked;
                        Button cancelButton = findViewById(getResources().getIdentifier(cancelButtonId, "id", getPackageName()));
                        Button addButton = findViewById(getResources().getIdentifier(addButtonId, "id", getPackageName()));
                    // Toggle the visibility of the Cancel/Add buttons
                        if (isAlarmAlreadySet) {
                            cancelButton.setVisibility(View.VISIBLE);
                            addButton.setVisibility(View.GONE);
                        } else {
                            cancelButton.setVisibility(View.GONE);
                            addButton.setVisibility(View.VISIBLE);
                        }
                    }
                    toggleAddCancel();
                }
                if (addButtonClicked != 0){
                    Log.d("QueryActivity", "addButtonClicked before: " + addButtonClicked);

                    if (alarm != null) {
                        boolean isAlarmAlreadySet = false;


                        // boolean isAlarmWithin30Min = false;


                            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + addButtonClicked, "id", getPackageName());
                            TextView textView = findViewById(textViewId);
                            if (textView.getText().toString().equals(alarm)) {
                                isAlarmAlreadySet = true;

                            }
                            if (textView.getText().toString().equals("Not set")) {
                                textView.setText(alarm);
                                isAlarmAlreadySet = true;

                            }

                        if (!isAlarmAlreadySet) {
                            Toast.makeText(QueryActivity.this, "Alarm already set", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        // Get references to the Cancel/Add buttons
                        String cancelButtonId = "CancelReminderbtn" + addButtonClicked;
                        String addButtonId = "AddReminderbtn" + addButtonClicked;
                        Button cancelButton = findViewById(getResources().getIdentifier(cancelButtonId, "id", getPackageName()));
                        Button addButton = findViewById(getResources().getIdentifier(addButtonId, "id", getPackageName()));

                        // Toggle the visibility of the Cancel/Add buttons
                        if (isAlarmAlreadySet) {
                            cancelButton.setVisibility(View.VISIBLE);
                            addButton.setVisibility(View.GONE);
                        } else {
                            cancelButton.setVisibility(View.GONE);
                            addButton.setVisibility(View.VISIBLE);
                        }


                        // Reset the addButtonClicked variable
                        addButtonClicked = 0;
                    }
                    toggleAddCancel();
                }






            }
        });

        binding.AddReminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClicked = 0;
                boolean isAlarmTableFull = true;
                for (int i = 1; i <= 10; i++) {
                    int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
                    TextView textView = findViewById(textViewId);

                    if (textView.getText().toString().equals("Not set")) {
                        isAlarmTableFull = false;
                    }
                }

                if (isAlarmTableFull) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("You cannot add any more alarms");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                } else {
                    // Call the timepicker method here
                    showTimePicker();
                }
            }


        });

        binding.SelectTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setSelection(0);
                alarmCancelAll();
                Toast.makeText(QueryActivity.this, "Alarms Cancelled", Toast.LENGTH_SHORT).show();
                cancelAlarm1();
                cancelAlarm2();
                cancelAlarm3();
                cancelAlarm4();
                cancelAlarm5();
                cancelAlarm6();
                cancelAlarm7();
                cancelAlarm8();
                cancelAlarm9();
                cancelAlarm10();
                showTimePicker();

            }
        });

        // Loop through each TableRow in the TableLayout

        TableLayout tableLayout = findViewById(R.id.tabla_cuerpo);
        for (int i = 1; i <= 10; i++) {

            // Generate the ID strings for the TextView and Cancel/Add button
            String textViewId = "id_cad_details_dialog_key" + i;
            String cancelButtonId = "CancelReminderbtn" + i;
            String addButtonId = "AddReminderbtn" + i;

            // Get a reference to the TableRow containing the TextView and Cancel/Add button
            int tableRowId = getResources().getIdentifier("tableRow" + i, "id", getPackageName());
            TableRow tableRow = findViewById(tableRowId);

            // Get references to the TextView, Cancel button, and Add button
            TextView textView = tableRow.findViewById(getResources().getIdentifier(textViewId, "id", getPackageName()));
            Button cancelButton = tableRow.findViewById(getResources().getIdentifier(cancelButtonId, "id", getPackageName()));
            Button addButton = tableRow.findViewById(getResources().getIdentifier(addButtonId, "id", getPackageName()));

            // Check if the first column is not set, and toggle the Cancel/Add button accordingly
            if (textView.getText().toString().equals("Not set")) {
                cancelButton.setVisibility(View.GONE);
                addButton.setVisibility(View.VISIBLE);
            } else {
                cancelButton.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.GONE);
            }

            // Set the OnClickListener for the Cancel/Add button to set the TextView to "Not set" and cancel the alarm or start the reminder activity
            final int requestCode = i;
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set the TextView to "Not set"
                    textView.setText("Not set");

                    // Cancel the corresponding PendingIntent from AlarmManager
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(QueryActivity.this, "Alarm deleted", Toast.LENGTH_SHORT).show();

                    // Toggle the Cancel/Add button
                    cancelButton.setVisibility(View.GONE);
                    addButton.setVisibility(View.VISIBLE);
                }
            });

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set addButtonClicked to the row number of the button clicked
                    addButtonClicked = requestCode;
                    boolean isAlarmTableFull = true;
                    for (int i = 1; i <= 10; i++) {
                        int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
                        TextView textView = findViewById(textViewId);


                        if (textView.getText().toString().equals("Not set")) {
                            isAlarmTableFull = false;
                        }
                    }

                    if (isAlarmTableFull) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("You cannot add any more alarms");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Do nothing
                            }
                        });
                        builder.create().show();
                    } else {
                        // Call the timepicker method here
                        showTimePicker();

                    }



                }
            });
        }








        /*TableLayout tableLayout = findViewById(R.id.tabla_cuerpo);
        for (int i = 1; i <= 10; i++) {
            // Generate the ID strings for the TextView and Cancel button
            String textViewId = "id_cad_details_dialog_key" + i;
            String cancelButtonId = "CancelReminderbtn" + i;

            // Get a reference to the TableRow containing the TextView and Cancel button
            int tableRowId = getResources().getIdentifier("tableRow" + i, "id", getPackageName());
            TableRow tableRow = findViewById(tableRowId);

            // Get references to the TextView and Cancel button
            TextView textView = tableRow.findViewById(getResources().getIdentifier(textViewId, "id", getPackageName()));
            Button cancelButton = tableRow.findViewById(getResources().getIdentifier(cancelButtonId, "id", getPackageName()));

            // Set the OnClickListener for the Cancel button to set the TextView to "Not set" and cancel the alarm
            final int requestCode = i;
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set the TextView to "Not set"
                    textView.setText("Not set");

                    // Cancel the corresponding PendingIntent from AlarmManager
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(QueryActivity.this, "Alarm deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }*/



        Button cancelAll = findViewById(R.id.CancelAll);

        cancelAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setSelection(0);
                Toast.makeText(QueryActivity.this, "All alarms deleted", Toast.LENGTH_SHORT).show();
                alarmCancelAll();
                cancelAlarm1();
                cancelAlarm2();
                cancelAlarm3();
                cancelAlarm4();
                cancelAlarm5();
                cancelAlarm6();
                cancelAlarm7();
                cancelAlarm8();
                cancelAlarm9();
                cancelAlarm10();
                toggleAddCancel();
            }

        });

        spinner = findViewById(R.id.spinner_query);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.DayQuery, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int item, long l) {
                if ( item ==1){
                    alarmCancelAll();
                    CreateAlarm8();
                    addAlarm8();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("1 alarm is automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 2 ) {
                    alarmCancelAll();
                    CreateAlarm2(); //8 am
                    addAlarm2();
                    CreateAlarm8(); //8 pm
                    addAlarm8();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("2 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 3) {
                    alarmCancelAll();
                    CreateAlarm2(); // 8 am
                    addAlarm2();
                    CreateAlarm5(); // 2 pm
                    addAlarm5();
                    CreateAlarm8(); // 8 pm
                    addAlarm8();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("3 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 4) {
                    alarmCancelAll();
                    CreateAlarm2(); // 8 am
                    addAlarm2();
                    CreateAlarm4(); // 12 pm
                    addAlarm4();
                    CreateAlarm6(); // 16 pm
                    addAlarm6();
                    CreateAlarm9(); //10 pm
                    addAlarm9();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("4 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 5) {
                    alarmCancelAll();
                    CreateAlarm1(); //6 am
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("5 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 6) {
                    alarmCancelAll();
                    CreateAlarm1();
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    CreateAlarm6();
                    addAlarm6();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("6 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

                if (item == 7) {
                    alarmCancelAll();
                    CreateAlarm1();
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    CreateAlarm6();
                    addAlarm6();
                    CreateAlarm7();
                    addAlarm7();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("7 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }
                if (item == 8) {
                    alarmCancelAll();
                    CreateAlarm1();
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    CreateAlarm6();
                    addAlarm6();
                    CreateAlarm7();
                    addAlarm7();
                    CreateAlarm8();
                    addAlarm8();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("8 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }
                if (item == 9) {
                    alarmCancelAll();
                    CreateAlarm1();
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    CreateAlarm6();
                    addAlarm6();
                    CreateAlarm7();
                    addAlarm7();
                    CreateAlarm8();
                    addAlarm8();
                    CreateAlarm9();
                    addAlarm9();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("9 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }
                if (item == 10) {
                    alarmCancelAll();
                    CreateAlarm1();
                    addAlarm1();
                    CreateAlarm2();
                    addAlarm2();
                    CreateAlarm3();
                    addAlarm3();
                    CreateAlarm4();
                    addAlarm4();
                    CreateAlarm5();
                    addAlarm5();
                    CreateAlarm6();
                    addAlarm6();
                    CreateAlarm7();
                    addAlarm7();
                    CreateAlarm8();
                    addAlarm8();
                    CreateAlarm9();
                    addAlarm9();
                    CreateAlarm10();
                    addAlarm10();
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("10 alarms are automatically set");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                    toggleAddCancel();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.getString("lang","de").equals("en")){
            setLanguage("en");
        }else{
            setLanguage("de");
        }

    }

//Create notification channel to group alarm manager notifications together
    private void createNotificationChannel() {
        if(android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O) {
            CharSequence name = "androidReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("android", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
//Create time picker
    private void showTimePicker() {

        picker = new MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(00)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(), "android");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (picker.getHour() >= 0 && picker.getHour() < 6) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Please do not choose the time between 00 AM and 06 AM");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                } else {
                    if (picker.getHour() > 12) {
                        binding.selectedTime.setText(picker.getHour() % 12 + " : " + String.format("%02d", picker.getMinute()) + " PM");
                    } else {
                        binding.selectedTime.setText(picker.getHour() + " : " + String.format("%02d", picker.getMinute()) + " AM");
                    }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND, 0);


            }}
        });

    }

    private String setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;

    }

//Create methods to cancel all the 10 alarms
    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }

    private void cancelAlarm1(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id1 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id1, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);


    }

    private void cancelAlarm2(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id2 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id2, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm3(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id3 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id3, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm4(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id4 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id4, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm5(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id5 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id5, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm6(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id6 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id6, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm7(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id7 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id7, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm8(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id8 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id8, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm9(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id9 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id9, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm10(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id10 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id10, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    //Create 10 alarms to distribute over the day
    public String CreateAlarm1() {
        // set alarm at approx. 06:00 pm
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id1 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id1, intent, 0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }




    public String CreateAlarm2() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id2 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id2, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm3() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id3 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id3, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm4() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id4 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id4, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm5() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id5 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id5, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm6() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id6 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id6, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm7() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,18 );
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id7 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id7, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm8() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id8 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id8, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm9() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id9 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id9, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    public String CreateAlarm10() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id10 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id10, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);


        // Create a Calendar object to represent the time you set for the alarm
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(calendar.getTimeInMillis());

        // Format the Calendar object to a String using a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTime = dateFormat.format(alarmTime.getTime());

        // Return the formatted String
        return formattedTime;
    }

    //Methods to add the alarms to the list
    private void addAlarm1() {
        String alarm = CreateAlarm1();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm2() {
        String alarm = CreateAlarm2();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm3() {
        String alarm = CreateAlarm3();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm4() {
        String alarm = CreateAlarm4();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm5() {
        String alarm = CreateAlarm5();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm6() {
        String alarm = CreateAlarm6();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm7() {
        String alarm = CreateAlarm7();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm8() {
        String alarm = CreateAlarm8();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm9() {
        String alarm = CreateAlarm9();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }
    private void addAlarm10() {
        String alarm = CreateAlarm10();
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (textView.getText().toString().equals("Not set")) {
                textView.setText(alarm);
                break; // exit loop after setting the first text view
            }
        }
    }


    //Methods for cards animation
    public void expand(View view) {
        int v = (backCard.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout1, new AutoTransition());
        backCard.setVisibility(v);

    }

    public void expand2(View view) {
        int v = (backCard2.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout2, new AutoTransition());
        backCard2.setVisibility(v);
    }

    //Cancel all alarms and update the alarm list
    private void alarmCancelAll() {
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (!textView.getText().toString().equals("Not set")) {
                textView.setText("Not set");

            }
        }

        cancelAlarm1();
        cancelAlarm2();
        cancelAlarm3();
        cancelAlarm4();
        cancelAlarm5();
        cancelAlarm6();
        cancelAlarm7();
        cancelAlarm8();
        cancelAlarm9();
        cancelAlarm10();
    }

    private void toggleAddCancel() {

        boolean isAnyAlarmSet = false;
        for (int i = 1; i <= 10; i++) {
            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);
            if (!textView.getText().toString().equals("Not set")) {
                isAnyAlarmSet = true;
                break;
            }
        }

        for (int i = 1; i <= 10; i++) {
            String cancelButtonId = "CancelReminderbtn" + i;
            String addButtonId = "AddReminderbtn" + i;
            Button cancelButton = findViewById(getResources().getIdentifier(cancelButtonId, "id", getPackageName()));
            Button addButton = findViewById(getResources().getIdentifier(addButtonId, "id", getPackageName()));

            int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
            TextView textView = findViewById(textViewId);

            // Toggle the visibility of the Cancel/Add buttons
            if (!textView.getText().toString().equals("Not set")) {
                cancelButton.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.GONE);
            } else {
                if (isAnyAlarmSet) {
                    cancelButton.setVisibility(View.GONE);
                } else {
                    cancelButton.setVisibility(View.VISIBLE);
                }
                addButton.setVisibility(View.VISIBLE);
            }
        }
    }






}

