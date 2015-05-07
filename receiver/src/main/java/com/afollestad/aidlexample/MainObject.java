package com.afollestad.aidlexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Aidan Follestad (afollestad)
 */
public class MainObject implements Parcelable {

    private String mValue;

    public MainObject(Parcel source) {
        mValue = source.readString();
    }

    public MainObject(String value) {
        mValue = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }

    public static final Creator<MainObject> CREATOR = new Creator<MainObject>() {
        @Override
        public MainObject[] newArray(int size) {
            return new MainObject[size];
        }

        @Override
        public MainObject createFromParcel(Parcel source) {
            return new MainObject(source);
        }
    };
}
