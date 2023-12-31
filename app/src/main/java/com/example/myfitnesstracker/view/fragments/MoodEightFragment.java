package com.example.myfitnesstracker.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.MoodDataDao;
import com.example.myfitnesstracker.view.activities.MainActivity;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodEightFragment extends Fragment {

    private Button button;
    private Button buttonBack;
    private SeekBar seekBar;
    private SeekBar seekBar2;
    private TextView textView;
    private TextView textView2;
    MainViewModel viewModel;
    private Button abbrechen;

    AppDatabase db;
    MoodDataDao moodDataDao;







    public MoodEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_eight, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        seekBar = view.findViewById(R.id.seekBar8);
        textView = view.findViewById(R.id.textView20);
        seekBar2 = view.findViewById(R.id.seekBar9);
        textView2 = view.findViewById(R.id.textView18);



        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        db= Room.databaseBuilder(getActivity().getApplicationContext(),AppDatabase.class,"Tracker_Database").build();
        moodDataDao = db.moodDataDao();
        seekBar.getThumb().setAlpha(0);
        final Handler seekBarHandler = new Handler(Looper.getMainLooper());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
                viewModel.setImpulsively(String.valueOf(progress));
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                if(progress == 0 ){
                    textView.setText("trifft gar nicht zu ");
                }
                else if(progress == 1 ){
                    textView.setText("trifft nicht zu  ");
                }
                else if(progress == 2 ){
                    textView.setText("trifft  zu  ");

                }
                else if(progress == 3 ){
                    textView.setText("trifft voll zu  ");

                }
                else {
                    textView.setText("trifft voll und ganz zu  ");

                }
                textView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.getThumb().setAlpha(0);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView2.setText(String.valueOf(progress2));
                viewModel.setAggressive(String.valueOf(progress2));
                int val = (progress2 * (seekBar2.getWidth() - 2 * seekBar2.getThumbOffset())) / seekBar2.getMax();
                if(progress2 == 0 ){
                    textView2.setText("trifft gar nicht zu ");
                }
                else if(progress2 == 1 ){
                    textView2.setText("trifft nicht zu  ");
                }
                else if(progress2 == 2 ){
                    textView2.setText("trifft  zu  ");

                }
                else if(progress2 == 3 ){
                    textView2.setText("trifft ganz zu  ");

                }
                else {
                    textView2.setText("trifft voll und ganz zu  ");

                }

                textView2.setX(seekBar2.getX() + val + seekBar2.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar2.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });


        button = (Button) view.findViewById(R.id.zurück7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(MoodEightFragment.this).navigate(R.id.action_moodEightFragmen_to_NotesFragment);


               Runnable runnable = new Runnable() {
                   @Override
                    public void run() {
                       moodDataDao.insertAll(viewModel.saveMoodData());
                       moodDataDao.getAll();
                    }

                };
                new Thread(runnable).start();

           }
        });
        buttonBack = view.findViewById(R.id.button_appraisal_back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(MoodEightFragment.this).navigate(R.id.action_moodEightFragment_to_moodSevenFragment);
            }
        });


        abbrechen = view.findViewById(R.id.abbrechen2);

        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(MoodEightFragment.this).navigate(R.id.action_moodEightFragment_to_moodSixFragment);
            }
        });

    }


}