package com.example.guest.oscars.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Guest on 3/23/16.
 */

@Parcel

public class Movie {
    String mImage;
    String mOverview;
    Date mReleaseDate;
    ArrayList<Genre> mGenres;
    Integer mId;
    String mTitle;
    Integer mVoteCount;
    Double mVoteAverage;

    public Movie () {

    }

    public Movie(String mImage, String mOverview, Date mReleaseDate, ArrayList<Genre> mGenres, Integer mId, String mTitle, Integer mVoteCount, Double mVoteAverage) {
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

    public Date getmReleaseDate() {
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

    public String getAllGenres() {
        String genre = "";

        for(int i = 0; i< mGenres.size(); i++) {
           genre += mGenres.get(i).getmName() + " ";
        }

        return genre;
    }

    public String getFirstGenre() {
        String genreName = "No genres listed for this film";
        for (int i=0; i<mGenres.size(); i++) {
            if (mGenres.get(i).getmName() != null) {
                genreName = mGenres.get(0).getmName();
            }
        }
        return genreName;
    }
}
