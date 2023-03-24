package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodThreeFragment extends Fragment {

    private Button buttonNo;
    private Button btnNextMoodThree;
    private Button abbrechen;
    MainViewModel viewModel;
    EditText textFragmentThree;

    public MoodThreeFragment() {
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
        return inflater.inflate(R.layout.fragment_mood_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonNo=view.findViewById(R.id.nein1);
        btnNextMoodThree = view.findViewById(R.id.ja1);
        textFragmentThree = view.findViewById(R.id.editTextTextMultiLine3);
        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        btnNextMoodThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textFragmentThree.getText())){
                    viewModel.setNotes(textFragmentThree.getText().toString());
                }
                NavHostFragment.findNavController(MoodThreeFragment.this).navigate(R.id.action_moodThreeFragment_to_moodFiveFragment);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(textFragmentThree.getText())){
                    viewModel.setNotes(textFragmentThree.getText().toString());
                }
                viewModel.setAlone("No");
                NavHostFragment.findNavController(MoodThreeFragment.this).navigate(R.id.action_moodThreeFragment_to_moodFourFragment);
            }
        });

        abbrechen = view.findViewById(R.id.abbrechen3);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textFragmentThree.getText())){
                    viewModel.setNotes(textFragmentThree.getText().toString());
                }
                viewModel.setAlone("Yes");
                NavHostFragment.findNavController(MoodThreeFragment.this).navigate(R.id.action_moodThreeFragment_to_moodSixFragment);
            }
        });

    }
}