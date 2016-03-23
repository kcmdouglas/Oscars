package com.example.guest.oscars.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.oscars.R;
import com.example.guest.oscars.services.MovieService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
    //public  ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        getMovies(intent.getStringExtra("url"));
    }

    private void getMovies(String APIUrl) {
        final MovieService movieService = new MovieService(this);

        movieService.findMovies(APIUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MovieService.processMovies(response);
            }
        });
    }
}
