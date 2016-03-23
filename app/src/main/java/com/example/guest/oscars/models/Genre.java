package com.example.guest.oscars.models;

import android.util.Log;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 3/23/16.
 */
@Parcel
public class Genre {
    String mName;
    Integer mId;
    static ArrayList<Genre> genreList = new ArrayList<>();


    public Genre() {

    }

    public Genre(String mName, Integer mId) {
        this.mName = mName;
        this.mId = mId;
        this.genreList.add(this);
        Log.d("Genre id: ", mId.toString());
        Log.d("Genre name: ", mName);
    }

    public Integer getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public static Genre getGenreById(Integer id) {
        for (int i=0; i<genreList.size(); i++) {
            if (genreList.get(i).getmId() == id) {
                return genreList.get(i);
            }
        }
        return null;
    }
}
