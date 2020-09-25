package com.codepath.laolmstead.flixster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String overview;
    String baseURL;
    String posterSize;
    public static final String TAG = "Movie";

    public Movie(JSONObject jsonObject, Size size) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
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
}
