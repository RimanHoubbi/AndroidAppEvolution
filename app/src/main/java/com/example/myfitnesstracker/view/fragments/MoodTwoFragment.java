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
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodTwoFragment extends Fragment {

    private Button button;
    private Button buttonBack;
    private Button abbrechen;
    private Button buttonJa1;
    private Button buttonNein1;
    private Button buttonJa2;
    private Button buttonNein2;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    public TextView textView;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;


    MainViewModel viewModel;



    public MoodTwoFragment() {
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
        return inflater.inflate(R.layout.fragment_mood_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar1 = view.findViewById(R.id.seekBarOne);
        textView = view.findViewById(R.id.progress7);
        seekBar2 =view.findViewById(R.id.seekBarTwo);
        textView2 = view.findViewById(R.id.progress8);
        button =view.findViewById(R.id.btn_next);
        buttonJa1 =view.findViewById(R.id.ja2);
        buttonJa2 =view.findViewById(R.id.ja3);
        buttonNein1 =view.findViewById(R.id.abbrechen8);
        buttonNein2 =view.findViewById(R.id.abbrechen9);
        textView5 = view.findViewById(R.id.sehrIntensiv2);
        textView6 = view.findViewById(R.id.keinEreignis2);
        textView3 = view.findViewById(R.id.keinEreignis);
        textView4 =view.findViewById(R.id.sehrIntensiv);


        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        seekBar1.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
        buttonJa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                if (seekBar1.getVisibility() == View.VISIBLE){
                    seekBar1.setVisibility(View.GONE);
                } else {
                    seekBar1.setVisibility(View.VISIBLE);
                }
            }
        });

        seekBar1.getThumb().setAlpha(0);
        final Handler seekBarHandler = new Handler(Looper.getMainLooper());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
                viewModel.setNegative_events(String.valueOf(progress));
                int val = (progress * (seekBar1.getWidth() - 2 * seekBar1.getThumbOffset())) / seekBar1.getMax();
                textView.setText("" + progress);
                textView.setX(seekBar1.getX() + val + seekBar1.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar1.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setVisibility(View.GONE);
        textView6.setVisibility(View.GONE);
        textView5.setVisibility(View.GONE);
        buttonJa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView5.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);
                if (seekBar2.getVisibility() == View.VISIBLE){
                    seekBar2.setVisibility(View.GONE);
                } else {
                    seekBar2.setVisibility(View.VISIBLE);
                }
            }
        });
        seekBar2.getThumb().setAlpha(0);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView2.setText(String.valueOf(progress2));
                viewModel.setPositive_events(String.valueOf(progress2));
                int val = (progress2 * (seekBar2.getWidth() - 2 * seekBar2.getThumbOffset())) / seekBar2.getMax();
                textView2.setText("" + progress2);
                textView2.setX(seekBar2.getX() + val + seekBar2.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHandler.removeCallbacksAndMessages(null);
                seekBar2.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                NavHostFragment.findNavController(MoodTwoFragment.this).navigate(R.id.action_moodTwoFragment_to_moodThreeFragment);
            }
        });

        abbrechen = view.findViewById(R.id.abbrechen2);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodTwoFragment.this).navigate(R.id.action_moodTwoFragment_to_moodSixFragment);
            }
        });

        buttonBack = view.findViewById(R.id.button_appraisal_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodTwoFragment.this).navigate(R.id.action_moodTwoFragment_to_moodOneFragment);
            }
        });
    }
}