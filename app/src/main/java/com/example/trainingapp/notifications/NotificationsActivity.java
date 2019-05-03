package com.example.trainingapp.notifications;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.trainingapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    LocalData localData;

    @BindView(R.id.bnSave)
    Button bnSave;

    @BindView(R.id.monday)
    CheckBox monday;

    @BindView(R.id.tuesday)
    CheckBox tuesday;

    @BindView(R.id.wednesday)
    CheckBox wednesday;

    @BindView(R.id.thursday)
    CheckBox thursday;

    @BindView(R.id.friday)
    CheckBox friday;

    @BindView(R.id.saturday)
    CheckBox saturday;

    @BindView(R.id.sunday)
    CheckBox sunday;

    @BindView(R.id.time)
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        initViews();
    }

    private String getFormattedTime(LocalData localData) {
        StringBuilder builder = new StringBuilder();
        if (localData.getHour() < 10) builder.append("0");
        builder.append(localData.getHour());
        builder.append("  :  ");
        if (localData.getMin() < 10) builder.append("0");
        builder.append(localData.getMin());
        return builder.toString();
    }

    private void initViews() {
        localData = new LocalData(this);
        time.setText(getFormattedTime(localData));
        getNotifiedDays();
        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRemindersForDays();
                onBackPressed();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }

    private void getNotifiedDays() {
        List<Integer> days = localData.getDays();
        for (Integer day : days) {
            if (day == 1) sunday.setChecked(true);
            if (day == 2) monday.setChecked(true);
            if (day == 3) tuesday.setChecked(true);
            if (day == 4) wednesday.setChecked(true);
            if (day == 5) thursday.setChecked(true);
            if (day == 6) friday.setChecked(true);
            if (day == 7) saturday.setChecked(true);
        }
    }

    private void setRemindersForDays() {
        List<Integer> days = new ArrayList<>();
        if (sunday.isChecked()) days.add(1);
        if (monday.isChecked()) days.add(2);
        if (tuesday.isChecked()) days.add(3);
        if (wednesday.isChecked()) days.add(4);
        if (thursday.isChecked()) days.add(5);
        if (friday.isChecked()) days.add(6);
        if (saturday.isChecked()) days.add(7);
        localData.setDays(days);
        setReminder();
    }

    private void showTimePicker() {
        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        localData.setHour(hour);
                        localData.setMin(min);
                        time.setText(getFormattedTime(localData));
                    }
                }, localData.getHour(), localData.getMin(), true);
        builder.show();
    }

    private void setReminder() {
        NotificationScheduler.cancelReminder(this, AlarmReceiver.class);
        for (Integer day : localData.getDays()) {
            NotificationScheduler.setReminder(
                    NotificationsActivity.this,
                    AlarmReceiver.class,
                    localData.getHour(),
                    localData.getMin(),
                    day
            );
        }
    }

}
