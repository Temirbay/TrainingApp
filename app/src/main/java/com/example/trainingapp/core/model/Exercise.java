package com.example.trainingapp.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
    public String name;
    public String body;

    public Exercise(String name, String body) {
        this.name = name;
        this.body = body;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        body = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(body);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}
