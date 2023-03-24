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

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodTwoFragment extends Fragment {

    private Button button;
    private Button abbrechen;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    public TextView textView;
    public TextView textView2;
    MainViewModel viewModel;
    EditText textFragmentTwo;


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
        textFragmentTwo = view.findViewById(R.id.editTextTextMultiLine2);
        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress) + "%");
                viewModel.setNegative_events(String.valueOf(progress));
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
                textView2.setText(String.valueOf(progress2) + "%");
                viewModel.setPositive_events(String.valueOf(progress2));

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
                if (!TextUtils.isEmpty(textFragmentTwo.getText())){
                    viewModel.setNotes(textFragmentTwo.getText().toString());
                }
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
    }
}