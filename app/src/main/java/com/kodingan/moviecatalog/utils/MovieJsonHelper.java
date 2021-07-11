package com.kodingan.moviecatalog.utils;

import android.content.Context;

import com.kodingan.moviecatalog.data.source.remote.response.MovieResponse;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieJsonHelper {

    private Context context;

    public MovieJsonHelper(Context context) {
        this.context = context;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> loadMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("MovieResponses.json")));
            JSONArray listArray = responseObject.getJSONArray("movies");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject movie = listArray.getJSONObject(i);

                String id = movie.getString("id");
                String title = movie.getString("title");
                String description = movie.getString("description");
                String date = movie.getString("date");
                String imagePath = movie.getString("imagePath");

                MovieResponse movieResponse = new MovieResponse(id, title, description, date, imagePath);
                list.add(movieResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MovieResponse> loadMovie(String courseId) {
        String fileName = String.format("Module_%s.json", courseId);
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            String result = parsingFileToString(fileName);
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);
                JSONArray listArray = responseObject.getJSONArray("modules");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject course = listArray.getJSONObject(i);

                    String id = course.getString("id");
                    String title = course.getString("title");
                    String description = course.getString("description");
                    String date = course.getString("date");
                    String imagePath = course.getString("imagePath");

                    MovieResponse movieResponse = new MovieResponse(id, title, description, date, imagePath);
                    list.add(movieResponse);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }



}

