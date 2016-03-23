package com.example.guest.oscars.services;

import android.content.Context;
import android.util.Log;

import com.example.guest.oscars.R;
import com.example.guest.oscars.models.Genre;
import com.example.guest.oscars.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<Movie> processMovies (Response response) {
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject moviesJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = moviesJSON.getJSONArray("results");
                for (int i=0; i<resultsJSON.length(); i++) {
                    JSONObject movieJSON = resultsJSON.getJSONObject(i);
                    String poster = "https://image.tmdb.org/t/p/w396" + movieJSON.getString("poster_path");
                    String overview = movieJSON.getString("overview");
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date releaseDate = format.parse(movieJSON.getString("release_date"));
                    JSONArray genresJSON = movieJSON.getJSONArray("genre_ids");
                    ArrayList<Genre> genresList = new ArrayList<>();
                    for (int j=0; j<genresJSON.length(); j++) {
                        Genre genre = Genre.getGenreById(genresJSON.getInt(j));
                        if (genre != null) {
                            genresList.add(genre);
                        }
                    }
                    Integer id = movieJSON.getInt("id");
                    String title = movieJSON.getString("original_title");
                    Integer voteCount = movieJSON.getInt("vote_count");
                    Double voteAverage = movieJSON.getDouble("vote_average");

                    Movie movie = new Movie(poster, overview, releaseDate, genresList, id, title, voteCount, voteAverage);
                    movieList.add(movie);

                    Log.d("poster: ", poster);
                    Log.d("overview: ", overview);
                    Log.d("release: ", releaseDate.toString());
                    Log.d("id: ", id.toString());
                    Log.d("title: ", title);
                    Log.d("voteCounte: ", voteCount.toString());
                    Log.d("voteAverage: ", voteAverage.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return movieList;
    }


}
