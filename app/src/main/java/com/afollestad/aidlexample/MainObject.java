package com.afollestad.aidlexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Aidan Follestad (afollestad)
 */
public class MainObject implements Parcelable {

    private String mPath;

    public MainObject(Parcel source) {
        mPath = source.readString();
    }

    public MainObject(String path) {
        mPath = path;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
    }

    public static final Parcelable.Creator<MainObject> CREATOR = new Parcelable.Creator<MainObject>() {
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
