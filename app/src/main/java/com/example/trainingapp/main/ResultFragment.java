package com.example.trainingapp.main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.trainingapp.R;
import com.example.trainingapp.model.CurrentUser;
import com.example.trainingapp.model.Date;
import com.example.trainingapp.model.Result;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.calories)
    TextView calories;

    @BindView(R.id.minutes)
    TextView minutes;

    @BindView(R.id.exercises)
    TextView exercises;

    @BindView(R.id.dateCard)
    CardView dateCard;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.barChart)
    BarChart chart;

    @BindView(R.id.month)
    TextView month;


    private Integer[] daysCountList = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private ArrayList<Result> results = new ArrayList<>();
    private Calendar calendar = Calendar.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_result,
                container,
                false
        );
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDate();
        initListeners();
        getResults();
    }

    private void getResults() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference()
                .child("users-results")
                .child(CurrentUser.uuid)
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("MSG", dataSnapshot.toString());
                       for (DataSnapshot item : dataSnapshot.getChildren()) {
                           Result result = new Result();
                           result.date = getDateFromString(item.getKey());
                           HashMap<String, Object> hashMap = (HashMap<String, Object>) item.getValue();
                           result.calories = Integer.parseInt(hashMap.get("calories").toString());
                           result.minutes = (Double) hashMap.get("minutes");
                           result.exercises = Integer.parseInt(hashMap.get("exercises").toString());
                           results.add(result);
                       }
                       showChart();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
    }

    @SuppressLint("SetTextI18n")
    private void showChart() {
        int month = calendar.get(Calendar.MONTH);
        int daysCount = daysCountList[month];

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        this.month.setText("Калории " + format.format(calendar.getTime()));

        ArrayList days = new ArrayList();
        ArrayList values = new ArrayList<>();
        for (int day = 1; day <= daysCount; day++)
            days.add(String.valueOf(day));
        for (int day = 1; day <= daysCount; day++) {
            Result result = getResultByDay(day);
            if (result == null) continue;
            values.add(new BarEntry(result.calories, day-1));
        }

        BarDataSet bardataset = new BarDataSet(values, "ККАЛ");
        BarData data = new BarData(days, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.invalidate();
        chart.setData(data);
    }



    private Result getResultByDay(int day) {
        for (Result result : results) {
            Date date = result.date;
            if (date.day == day
                    && calendar.get(Calendar.MONTH) == date.month
                    && calendar.get(Calendar.YEAR) == date.year) {
                return result;
            }
        }
        return null;
    }

    private Date getDateFromString(String dateString) {
        String[] parts = dateString.split("-");
        Date date = new Date();
        date.year = Integer.parseInt(parts[0]);
        date.month = Integer.parseInt(parts[1]);
        date.day = Integer.parseInt(parts[2]);
        return date;
    }

    private void initListeners() {
        dateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        R.style.datepicker,
                        ResultFragment.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
    }

    private void initDate() {
        date.setText(getDateString(calendar));
        showChart();
        Date date = new Date(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        showProgress();
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
                        if (hashMap == null) {
                            calories.setText("0");
                            minutes.setText("0");
                            exercises.setText("0");
                        } else {
                            if (hashMap.containsKey("calories"))
                                calories.setText(hashMap.get("calories").toString());
                            if (hashMap.containsKey("minutes"))
                            minutes.setText(String.format("%.2f", hashMap.get("minutes")));
                            if (hashMap.containsKey("exercises"))
                            exercises.setText(hashMap.get("exercises").toString());
                        }
                        hideProgress();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        this.calendar = calendar;
        initDate();
    }

    private String getDateString(Calendar date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("d MMMM, yyyy");
        return format.format(date.getTime());
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
