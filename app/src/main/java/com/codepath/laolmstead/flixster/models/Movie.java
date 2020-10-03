package com.codepath.laolmstead.flixster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieId;
    String posterPath;
    String backdropPath;
    String title;
    String overview;
    String baseURL;
    String posterSize;
    double rating;
    public static final String TAG = "Movie";

    // Empty constructor needed by the Parceler library.
    public Movie() {}

    public Movie(JSONObject jsonObject, Size size) throws JSONException {
        movieId = jsonObject.getInt("id");
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        baseURL = size.getBaseURL();
        posterSize = size.getPosterSize();
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray, Size size) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i), size));
        }
        return movies;
    }

    public String getPosterPath() {
        Log.i(TAG, baseURL + posterSize + posterPath);
        return baseURL + posterSize + posterPath;
    }

    public String getBackdropPath() {
        return baseURL + posterSize + backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }
}
