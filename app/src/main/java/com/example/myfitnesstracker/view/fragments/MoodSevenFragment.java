package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;

public class MoodSevenFragment extends Fragment {

    private Button button20;
    private Button back;
    private Button abbrechen7;
    private ProgressBar progressBar;
    private SeekBar seekBar14;
    private SeekBar seekBar15;
    public TextView textView14;
    public TextView textView15;
    MainViewModel viewModel;

    public MoodSevenFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_seven, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar14 = view.findViewById(R.id.seekBar14);
        textView14 = view.findViewById(R.id.textView14);
        seekBar15 =view.findViewById(R.id.seekBar15);
        textView15 = view.findViewById(R.id.textView15);
        button20 =view.findViewById(R.id.next);

        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        seekBar14.getThumb().setAlpha(0);
        final Handler seekBarHandler = new Handler(Looper.getMainLooper());
        seekBar14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView14.setText(progress + "%");
                viewModel.setNegative_events(String.valueOf(progress));
                int val = (progress * (seekBar14.getWidth() - 2 * seekBar14.getThumbOffset())) / seekBar14.getMax();
                textView14.setText("" + progress);
                textView14.setX(seekBar14.getX() + val + seekBar14.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar14.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar15.getThumb().setAlpha(0);
        seekBar15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView15.setText(progress2 + "%");
                viewModel.setPositive_events(String.valueOf(progress2));
                int val = (progress2 * (seekBar15.getWidth() - 2 * seekBar15.getThumbOffset())) / seekBar15.getMax();
                textView15.setText("" + progress2);
                textView15.setX(seekBar2.getX() + val + seekBar15.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar15.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(MoodSevenFragment.this).navigate(R.id.action_moodSevenFragment_to_moodEightFragment);
            }
        });

        abbrechen7 = view.findViewById(R.id.abbrechen7);
        abbrechen7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodSevenFragment.this).navigate(R.id.action_moodSevenFragment_to_moodSixFragment);
            }
        });
        back = view.findViewById(R.id.button_appraisal_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodSevenFragment.this).navigate(R.id.action_moodSevenFragment_to_moodFiveFragment);
            }
        });
    }
}