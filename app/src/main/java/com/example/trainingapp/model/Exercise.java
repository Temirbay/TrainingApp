package com.example.trainingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
    public String name;
    public String body;
    public String gif;

    public Exercise(String name, String body, String gif) {
        this.name = name;
        this.body = body;
        this.gif = gif;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        body = in.readString();
        gif = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(body);
        dest.writeString(gif);
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
