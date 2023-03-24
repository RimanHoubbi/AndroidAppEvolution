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

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodSixFragment extends Fragment {

    private Button button;
    MainViewModel viewModel;
    EditText textFragmentSix;

    public MoodSixFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_six, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.zurück5);
        textFragmentSix =view.findViewById(R.id.editTextTextMultiLine8);
        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textFragmentSix.getText())){
                    viewModel.setNotes(textFragmentSix.getText().toString());
                }
                getActivity().finish();
                viewModel.setMoodEndTime(System.currentTimeMillis());
            }
        });

    }
}