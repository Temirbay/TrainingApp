package com.example.trainingapp.exercises;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trainingapp.R;
import com.example.trainingapp.model.Exercise;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.gif)
    ImageView gif;

    @BindView(R.id.body)
    TextView body;

    @BindView(R.id.nameLabel)
    TextView name;

    @BindView(R.id.next)
    ImageView next;

    @BindView(R.id.prev)
    ImageView prev;

    @BindView(R.id.counter)
    TextView counter;

    @BindView(R.id.timer)
    TextView timerView;


    private Exercise exercise;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private int currentExerciseIndex = 0;

    private int seconds = 0;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        ButterKnife.bind(this);

        initListeners();
        getIntents();
        initViews();
    }


    private void startTimer() {
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (seconds == 0) {
                            nextExercise();
                        } else {
                            seconds -= 1;
                            timerView.setText(getTimeString());
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void finishExercises() {

    }


    private String getTimeString(){
        int hour = seconds / 3600;
        seconds -= (seconds / 3600 * 3600);
        int minute =  seconds / 60;
        int second = seconds % 60;
        StringBuilder builder = new StringBuilder();
        if (hour < 10) builder.append("0");
        builder.append(hour);
        builder.append(":");
        if (minute < 10) builder.append("0");
        builder.append(minute);
        builder.append(":");
        if (second < 10) builder.append("0");
        builder.append(second);
        return builder.toString();
    }


    private void stopTimer () {
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    private void getIntents() {
        exercise = (Exercise)getIntent().getExtras().get("exercise");
        exercises = getIntent().getParcelableArrayListExtra("exercises");
    }

    private void initViews() {
        if (exercise != null) {
            Glide.with(this).asGif().load(exercise.gif).into(gif);
            body.setText(exercise.body);
            name.setText(exercise.name);
        }
        currentExerciseIndex = getCurrentExerciseIndex();
        counter.setText(String.valueOf(currentExerciseIndex+1));
        startTimer();
    }

    private void initListeners() {
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
    }


    private void nextExercise() {
        if (currentExerciseIndex == exercises.size()-1)  {
            finishExercises();
        }
        else {
            seconds = 60;
            exercise = exercises.get(currentExerciseIndex+1);
            initViews();
        }
    }


    private void prevExercise() {
        if (currentExerciseIndex == 0) {
            finishExercises();
        }
        else {
            seconds = 60;
            exercise = exercises.get(currentExerciseIndex-1);
            initViews();
        }
    }


    private int getCurrentExerciseIndex() {
        for (int position = 0; position < exercises.size(); position++) {
            if (exercises.get(position).equals(exercise))
                return position;
        }
        return 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev:
                prevExercise();
                break;
            case R.id.next:
                nextExercise();
                break;
        }
    }
}
