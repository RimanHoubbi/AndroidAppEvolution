package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NotesFragment extends Fragment {
    private static final int DISCARD_THRESHOLD = 3;
    private Button Finish;
    private Button buttonBack;
    MainViewModel viewModel;
    private Button abbrechen;

    private TextInputEditText editTextSurveyNote;

    public String getNote() {
        String note = Objects.requireNonNull(editTextSurveyNote.getText()).toString();

        if (note.length() < DISCARD_THRESHOLD) {
            return null;
        }

        return note;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        buttonBack = view.findViewById(R.id.button_appraisal_back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(NotesFragment.this).navigate(R.id.action_NotesFragment_to_moodEightFragment);
            }
        });

        buttonBack = view.findViewById(R.id.abbrechen2);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMoodEndTime(System.currentTimeMillis());
                NavHostFragment.findNavController(NotesFragment.this).navigate(R.id.action_NotesFragment_to_moodSixFragment);
            }
        });

        FragmentActivity activity = requireActivity();
        editTextSurveyNote = activity.findViewById(R.id.editTextSurveyNote);
        MaterialButton buttonSurveyNoteClear = activity.findViewById(R.id.buttonSurveyNoteClear);
        Finish = activity.findViewById(R.id.Finish);

        buttonSurveyNoteClear.setOnClickListener(v -> editTextSurveyNote.setText(""));


    }
}