package com.example.guest.oscars.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.guest.oscars.R;
import com.example.guest.oscars.models.Genre;
import com.example.guest.oscars.services.MovieService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.newReleasesButton) Button mNewReleasesButton;
    @Bind(R.id.upcomingButton) Button mUpcomingButton;
    @Bind(R.id.backgroundMain) ImageView mBackgroundMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNewReleasesButton.setOnClickListener(this);
        mUpcomingButton.setOnClickListener(this);

        getGenres();

        Picasso.with(this).load(R.drawable.theater_background).fit().centerCrop().into(mBackgroundMain);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent (MainActivity.this, ResultsActivity.class);
        if (v == mNewReleasesButton) {
            intent.putExtra("url", getString(R.string.now_playing));
        } else if (v == mUpcomingButton) {
            intent.putExtra("url", getString(R.string.upcoming_movies));
        }
        startActivity(intent);
    }

    private void getGenres() {
        final MovieService movieService = new MovieService(this);

        movieService.findGenres(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MovieService.processGenres(response);
            }
        });
    }
}
