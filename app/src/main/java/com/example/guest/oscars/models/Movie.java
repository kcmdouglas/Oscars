package com.example.guest.oscars.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Guest on 3/23/16.
 */

@Parcel

public class Movie {
    String mImage;
    String mOverview;
    String mReleaseDate;
    ArrayList<Genre> mGenres;
    Integer mId;
    String mTitle;
    Integer mVoteCount;
    Double mVoteAverage;

    public Movie(String mImage, String mOverview, String mReleaseDate, ArrayList<Genre> mGenres, Integer mId, String mTitle, Integer mVoteCount, Double mVoteAverage) {
        this.mImage = mImage;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mGenres = mGenres;
        this.mId = mId;
        this.mTitle = mTitle;
        this.mVoteCount = mVoteCount;
        this.mVoteAverage = mVoteAverage;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmOverview() {
        return mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public ArrayList<Genre> getmGenres() {
        return mGenres;
    }

    public Integer getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Integer getmVoteCount() {
        return mVoteCount;
    }

    public Double getmVoteAverage() {
        return mVoteAverage;
    }
}
