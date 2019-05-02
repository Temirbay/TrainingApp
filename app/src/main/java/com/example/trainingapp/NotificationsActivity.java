package com.example.trainingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
