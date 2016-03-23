package com.example.guest.oscars.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.oscars.R;
import com.example.guest.oscars.activity.ResultsActivity;
import com.example.guest.oscars.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/23/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder (view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.posterImageView) ImageView mPosterImageView;
        @Bind(R.id.movieTitleTextView) TextView mMovieTitleTextView;
        @Bind(R.id.genreTextView) TextView mGenreTextView;
        @Bind(R.id.releaseTextView) TextView mReleaseTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    //Intent intent = new Intent(mContext, MovieDetailActivity.class);
//                    intent.putExtra("position", itemPosition + "");
//                    intent.putExtra("movies", Parcels.wrap(mMovies));
//                    mContext.startActivity(intent);

                }
            });
        }

        public void bindMovie(Movie movie) {
            final Movie pMovie = movie;

            if(movie.getmImage().equals("https://image.tmdb.org/t/p/w396null")) {
                Picasso.with(mContext).load(R.drawable.placeholder_poster).fit().into(mPosterImageView);
            } else {
                Picasso.with(mContext).load(movie.getmImage()).fit().into(mPosterImageView);
            }
            mMovieTitleTextView.setText(movie.getmTitle());
            mGenreTextView.setText(movie.getFirstGenre() + " ...");
            mGenreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, pMovie.getAllGenres(), Toast.LENGTH_SHORT).show();
                }
            });

            mReleaseTextView.setText(movie.getmReleaseDateString().toString());
            mRatingTextView.setText(movie.getmVoteAverage().toString() + "/10");

        }

    }

}
