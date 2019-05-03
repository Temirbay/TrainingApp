package com.example.trainingapp.exercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trainingapp.R;
import com.example.trainingapp.model.Exercise;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailsActivity extends AppCompatActivity {

    @BindView(R.id.gif)
    ImageView gif;

    @BindView(R.id.body)
    TextView body;

    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        ButterKnife.bind(this);
        exercise = (Exercise)getIntent().getExtras().get("exercise");
        if (exercise != null) {
            Glide.with(this).asGif().load(exercise.gif).into(gif);
            body.setText(exercise.body);
        }
    }
}
