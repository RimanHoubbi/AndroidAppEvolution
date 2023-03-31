package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.myfitnesstracker.viewmodel.MainViewModel;

public class MoodOneFragment extends Fragment {

    long surveyStartTime;
    AppDatabase db;
    private Button button;
    private Button abbrechen;
    private EditText textFragmentOne;
    MainViewModel viewModel;

    public MoodOneFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        surveyStartTime = System.currentTimeMillis();
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "mooddb").build();
        SeekBar seekBar1 = view.findViewById(R.id.seekBarID1);
        TextView textView1 = view.findViewById(R.id.progress1);
        SeekBar seekBar2 = view.findViewById(R.id.seekBarID2);
        TextView textView2 = view.findViewById(R.id.progress2);
        SeekBar seekBar3 = view.findViewById(R.id.seekBarID3);
        TextView textView3 = view.findViewById(R.id.progress3);
        SeekBar seekBar4 = view.findViewById(R.id.seekBarID4);
        TextView textView4 = view.findViewById(R.id.progress4);
        SeekBar seekBar5 = view.findViewById(R.id.seekBarID5);
        TextView textView5 = view.findViewById(R.id.progress5);
        SeekBar seekBar6 = view.findViewById(R.id.seekBarID6);
        TextView textView6 = view.findViewById(R.id.progress6);
        button = view.findViewById(R.id.btnMoodOneNext);
        textFragmentOne = view.findViewById(R.id.editTextTextMultiLine);

        viewModel.setMoodStartTime(System.currentTimeMillis());

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar1, int progress1, boolean fromUser) {
                textView1.setText(String.valueOf(progress1));
                viewModel.setSatisfiedMeter(String.valueOf(progress1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView2.setText(String.valueOf(progress2));
                viewModel.setCalmMeter(String.valueOf(progress2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar3, int progress3, boolean fromUser) {
                textView3.setText(String.valueOf(progress3));
                viewModel.setHappinessMeter(String.valueOf(progress3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar4, int progress4, boolean fromUser) {
                textView4.setText(String.valueOf(progress4));
                viewModel.setExcitedMeter(String.valueOf(progress4));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar5, int progress5, boolean fromUser) {
                textView5.setText(String.valueOf(progress5));
                viewModel.setEnergyMeter(String.valueOf(progress5));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar6, int progress6, boolean fromUser) {
                textView6.setText(String.valueOf(progress6));
                viewModel.setSleepyMeter(String.valueOf(progress6));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textFragmentOne.getText())){
                    viewModel.setNotes(textFragmentOne.getText().toString());
                }
                NavHostFragment.findNavController(MoodOneFragment.this).navigate(R.id.action_moodOneFragment_to_moodTwoFragment);
            }
        });

        abbrechen = view.findViewById(R.id.abbrechen1);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodOneFragment.this).navigate(R.id.action_moodOneFragment_to_moodSixFragment);
            }
        });
    }
}