package com.example.trainingapp.exercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.model.Exercise;

public class ExerciseDetailsActivity extends AppCompatActivity {

    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        exercise = getIntent().getParcelableExtra("exercise");
    }
}
