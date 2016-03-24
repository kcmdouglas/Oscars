package com.example.guest.oscars.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.guest.oscars.R;
import com.example.guest.oscars.adapters.MoviePagerAdapter;
import com.example.guest.oscars.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity  {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private MoviePagerAdapter mAdapter;
    ArrayList<Movie> mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        mMovie = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        mAdapter = new MoviePagerAdapter(getSupportFragmentManager(), mMovie);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(startingPosition);

//        mShareIcon.setOnClickListener(this);
    }



    //inflate the menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    //Determine if actionBar item was selected. If true then do corresponding actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share:


                Movie movie = mMovie.get(mViewPager.getCurrentItem());
                String movieUrl = "https://www.themoviedb.org/movie/" + movie.getmId();
                String movieTitle = movie.getmTitle();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Let's see this movie: \n" + movieTitle + "\n" + movieUrl);
                sendIntent.setType("text/plain");
                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(sendIntent,
                        PackageManager.MATCH_ALL);

                boolean isIntentSafe = activities.size() > 0;
                if (isIntentSafe) {
                    startActivity(Intent.createChooser(sendIntent, "Select An App:"));
                    Log.d("Intent response: ", "SEND");
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




