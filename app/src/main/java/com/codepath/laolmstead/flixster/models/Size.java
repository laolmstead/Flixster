package com.codepath.laolmstead.flixster.models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class Size {

    String baseURL;
    String posterSize;
    public static final String TAG = "Size";
    public static final String CONFIGURATION_URL = "https://api.themoviedb.org/3/configuration?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public Size() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(CONFIGURATION_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject imageObject = json.jsonObject;
                try {
                    JSONObject images = imageObject.getJSONObject("images");
                    Log.i(TAG, "Images: " + images.toString());
                    baseURL = images.getString("secure_base_url");
                    List<String> posterSizes = new ArrayList<>();
                    JSONArray jsonArray = images.getJSONArray("poster_sizes");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        posterSizes.add(jsonArray.getString(i));
                    }
                    posterSize = posterSizes.get(3);
                } catch (JSONException e) {
                    Log.e(TAG, "HIT JSON EXCEPTION", e);
                }
            }
            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getPosterSize() {
        return posterSize;
    }
}
