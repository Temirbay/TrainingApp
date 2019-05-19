package com.example.trainingapp.model;

public class Date {
    public int year;
    public int month;
    public int day;

    public Date() { }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
