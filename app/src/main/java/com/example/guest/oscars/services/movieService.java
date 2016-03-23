package com.example.guest.oscars.services;

import android.content.Context;

import com.example.guest.oscars.R;

import okhttp3.Callback;

/**
 * Created by Guest on 3/23/16.
 */
public class movieService {
    private Context mContext;

    public movieService(Context mContext) {
        this.mContext = mContext;
    }

    public findUpcomingMovies(Callback callback) {
        final String KEY = mContext.getString(R.string.api_key);
        final String URL = mContext.getString(R.string.upcoming_movies);

    }
}
