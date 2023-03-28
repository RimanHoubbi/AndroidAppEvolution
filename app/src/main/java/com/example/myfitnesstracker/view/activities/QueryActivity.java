package com.example.myfitnesstracker.view.activities;

import android.animation.LayoutTransition;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class QueryActivity extends LocalizationActivity {



private ActivityQueryBinding binding;
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
                if (alarm != null) {
                    for (int i = 1; i <= 5; i++) {
                        int textViewId = getResources().getIdentifier("id_cad_details_dialog_key" + i, "id", getPackageName());
                        TextView textView = findViewById(textViewId);
                        if (textView.getText().toString().equals("Not set")) {
                            textView.setText(alarm);
                            break; // exit loop after setting the first text view
                        }
                    }
                    }




            }
        });

        binding.CancelReminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();

            }

        });

        binding.SelectTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();

            }
        });

        Button cancelAll = findViewById(R.id.CancelAll);
        cancelAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QueryActivity.this, "Alarms Cancelled", Toast.LENGTH_LONG).show();
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
                    CreateAlarm8();
                }

                if (item == 2 ) {
                    CreateAlarm2(); //8 am
                    CreateAlarm8(); //8 pm
                }

                if (item == 3) {
                    CreateAlarm2(); // 8 am
                    CreateAlarm5(); // 2 pm
                    CreateAlarm8(); // 8 pm
                }

                if (item == 4) {
                    CreateAlarm2(); // 8 am
                    CreateAlarm4(); // 12 pm
                    CreateAlarm6(); // 16 pm
                    CreateAlarm9(); //10 pm
                }

                if (item == 5) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                }

                if (item == 6) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                }

                if (item == 7) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                }
                if (item == 8) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                }
                if (item == 9) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                    CreateAlarm9();
                }
                if (item == 10) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                    CreateAlarm9();
                    CreateAlarm10();
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
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(), "android");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(picker.getHour() > 12) {
                    binding.selectedTime.setText(picker.getHour()%12 + " : " + picker.getMinute() + " PM");
                } else{
                    binding.selectedTime.setText(picker.getHour() + " : " + picker.getMinute() + " AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND, 0);


            }
        });

    }

    private String setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_LONG).show();

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
    public void CreateAlarm1() {
        // set alarm at approx. 06:00 pm
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id1 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id1, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 06:00 Uhr zeigen!", Toast.LENGTH_LONG).show();
    }

    public void CreateAlarm2() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,22 );
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id2 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id2, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 08:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm3() {
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
        Toast.makeText(this, "Erinnerung um 10:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm4() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 5);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id4 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id4, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 12:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm5() {
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
        Toast.makeText(this, "Erinnerung um 14:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm6() {
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
        Toast.makeText(this, "Erinnerung um 16:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm7() {
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
        Toast.makeText(this, "Erinnerung um 18:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm8() {
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
        Toast.makeText(this, "Erinnerung um 20:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm9() {
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
        Toast.makeText(this, "Erinnerung um 22:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm10() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 48);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id10 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id10, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 24:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

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

    //Alarms as list


}

