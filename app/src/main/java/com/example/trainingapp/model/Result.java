package com.example.trainingapp.model;

public class Result {
    public Date date;
    public int exercises;
    public int calories;
    public double minutes;

    public Result() { }

    public Result(Date date, int exercises, int calories, double minutes) {
        this.date = date;
        this.exercises = exercises;
        this.calories = calories;
        this.minutes = minutes;
    }
}
