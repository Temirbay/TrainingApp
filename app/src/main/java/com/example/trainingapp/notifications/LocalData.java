package com.example.trainingapp.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LocalData {

    private static final String APP_SHARED_PREFS = "TrainingAppPref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String hour = "hour";
    private static final String min = "min";
    private static final String days = "days";

    @SuppressLint("CommitPrefEdits")
    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public int getHour() {
        return appSharedPrefs.getInt(hour, 20);
    }

    public void setHour(int h) {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    public void setDays(List<Integer> d) {
        StringBuilder str = new StringBuilder();
        for (Integer integer : d)
            str.append(integer).append(",");
        prefsEditor.putString(days, str.toString());
        prefsEditor.commit();
    }

    public List<Integer> getDays () {
        String savedString = appSharedPrefs.getString(days, "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        List<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }

    public int getMin() {
        return appSharedPrefs.getInt(min, 0);
    }

    public void setMin(int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

    public void reset() {
        prefsEditor.clear();
        prefsEditor.commit();

    }

}