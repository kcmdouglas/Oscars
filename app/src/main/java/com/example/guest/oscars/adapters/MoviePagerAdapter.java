package com.example.guest.oscars.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.oscars.activity.MovieDetailFragment;
import com.example.guest.oscars.models.Movie;

import java.util.ArrayList;

/**
 * Created by Guest on 3/24/16.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Movie> mMovies;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<Movie> movies) {
        super(fm);
        mMovies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mMovies.get(position));
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovies.get(position).getmTitle();
    }

}
