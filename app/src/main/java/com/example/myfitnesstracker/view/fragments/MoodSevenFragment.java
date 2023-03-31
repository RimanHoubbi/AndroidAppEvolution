package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;

public class MoodSevenFragment extends Fragment {

    private Button button;
    private Button abbrechen;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
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
        button = (Button) view.findViewById(R.id.zurück6);
        ratingBar1 = view.findViewById(R.id.ratingBar1);
        ratingBar2 = view.findViewById(R.id.ratingBar2);

        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.setSatisfiedrate(String.valueOf(ratingBar1.getRating()));
                viewModel.setFailurerate(String.valueOf(ratingBar2.getRating()));
                NavHostFragment.findNavController(MoodSevenFragment.this).navigate(R.id.action_moodSevenFragment_to_moodEightFragment);
            }
        });

        ratingBar1 = view.findViewById(R.id.ratingBar1);
        ratingBar1.setNumStars(10);
        ratingBar1.setStepSize((float) 1);
        button = (Button) view.findViewById(R.id.abgeben1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = String.valueOf(ratingBar1.getRating());
                viewModel.setSatisfiedrate(s);
                Toast.makeText(requireContext(), s+"Star", Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar2 = view.findViewById(R.id.ratingBar2);
        ratingBar2.setNumStars(10);
        ratingBar2.setStepSize((float) 1);
        button = (Button) view.findViewById(R.id.abgeben2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = String.valueOf(ratingBar2.getRating());
                viewModel.setFailurerate(s);
                Toast.makeText(requireContext(), s+"Star", Toast.LENGTH_SHORT).show();
            }
        });


        abbrechen = view.findViewById(R.id.abbrechen7);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MoodSevenFragment.this).navigate(R.id.action_moodSevenFragment_to_moodSixFragment);
            }
        });
    }
}