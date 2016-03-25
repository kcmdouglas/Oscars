package com.example.guest.oscars.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.oscars.R;
import com.example.guest.oscars.adapters.MovieListAdapter;
import com.example.guest.oscars.models.Movie;
import com.example.guest.oscars.services.MovieService;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailFragment extends Fragment{
    @Bind(R.id.movieImageView)
    ImageView mImageLabel;
    @Bind(R.id.movieNameTextView)
    TextView mNameLabel;
    @Bind(R.id.genreTextView) TextView mGenreLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.overviewTextView) TextView mOverviewLabel;
    @Bind(R.id.releaseTextView) TextView mReleaseLabel;

    private Movie mMovie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment restaurantDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovie.getmImage()).into(mImageLabel);
        mNameLabel.setText(mMovie.getmTitle());
        mGenreLabel.setText(android.text.TextUtils.join(", ", mMovie.getAllGenres()));
        mRatingLabel.setText(Double.toString(mMovie.getmVoteAverage()) + "/10");
        mOverviewLabel.setText(mMovie.getmOverview());
        mReleaseLabel.setText(mMovie.getmReleaseDateString());

        return view;
    }


}