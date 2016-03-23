package com.example.guest.oscars.services;

import android.content.Context;

import com.example.guest.oscars.R;
import com.example.guest.oscars.models.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 3/23/16.
 */
public class MovieService {
    private Context mContext;
    private final String KEY;

    public MovieService(Context mContext) {
        this.mContext = mContext;
        KEY = mContext.getString(R.string.api_key);

    }

    public void findMovies(String APIurl, Callback callback) {
        final String URL = APIurl;

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(URL).newBuilder();
        urlBuilder.addQueryParameter("api_key", KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void findGenres(Callback callback) {
        final String URL = mContext.getString(R.string.genre_list);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(URL).newBuilder();
        urlBuilder.addQueryParameter("api_key", KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
            call.enqueue(callback);
    }

    public static void processGenres (Response response) {
//        ArrayList<Genre> genres = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray genresJSON = movieJSON.getJSONArray("genres");
                for(int i = 0; i < genresJSON.length(); i++) {
                    JSONObject genre = genresJSON.getJSONObject(i);
                    String name = genre.getString("name");
                    Integer id = genre.getInt("id");

                    Genre newGenre = new Genre(name, id);
//                    genres.add(newGenre);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        return genres;
    }


}
