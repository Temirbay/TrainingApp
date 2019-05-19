package com.example.trainingapp.exercises;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trainingapp.R;
import com.example.trainingapp.main.MainActivity;
import com.example.trainingapp.model.CurrentUser;
import com.example.trainingapp.model.Date;
import com.example.trainingapp.model.Exercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

    private int seconds = 60;
    private float totalSeconds = 0;
    private int totalExercises = 0;
    private int userExercises = 0;
    private double userMinutes = 0;
    private double userCalories = 0;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        ButterKnife.bind(this);

        getUserData();
        initListeners();
        getIntents();
        initViews();
    }

    private void getUserData() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference()
                .child("users-results")
                .child(CurrentUser.uuid)
                .child(date.toString())
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, Object> hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
                        if (hashMap != null) {
                            if (hashMap.containsKey("calories"))
                                userCalories = Integer.parseInt(hashMap.get("calories").toString());
                            if (hashMap.containsKey("minutes"))
                                userMinutes = (Double) hashMap.get("minutes");
                            if (hashMap.containsKey("exercises"))
                                userExercises = Integer.parseInt(hashMap.get("exercises").toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
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
                            totalExercises += 1;
                        } else {
                            seconds -= 1;
                            totalSeconds += 1;
                            timerView.setText(getTimeString());
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void finishExercises() {
        AlertDialog builder = new AlertDialog.Builder(ExerciseDetailsActivity.this, R.style.DialogTheme)
                .setTitle("Поздравляем!")
                .setMessage("Вы закончили все упражнения")
                .setPositiveButton("Вернуться на главную", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ExerciseDetailsActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        builder.show();
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

    @Override
    protected void onStop() {
        super.onStop();
        Calendar calendar = Calendar.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Date date = new Date(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        database.getReference()
                .child("users-results")
                .child(CurrentUser.uuid)
                .child(date.toString())
                .child("minutes")
                .setValue(userMinutes + totalSeconds/60);

        database.getReference()
                .child("users-results")
                .child(CurrentUser.uuid)
                .child(date.toString())
                .child("calories")
                .setValue(userCalories + totalSeconds*10);

        database.getReference()
                .child("users-results")
                .child(CurrentUser.uuid)
                .child(date.toString())
                .child("exercises")
                .setValue(userExercises + totalExercises);
    }

    private void prevExercise() {
        if (currentExerciseIndex != 0) {
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
