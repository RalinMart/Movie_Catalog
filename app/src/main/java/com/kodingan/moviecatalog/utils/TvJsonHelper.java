package com.kodingan.moviecatalog.utils;

import android.content.Context;

import com.kodingan.moviecatalog.data.source.remote.response.TvResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvJsonHelper {

    private Context context;

    public TvJsonHelper(Context context) {
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

    public List<TvResponse> loadTvs() {
        ArrayList<TvResponse> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("TvResponses.json")));
            JSONArray listArray = responseObject.getJSONArray("tvs");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject tv = listArray.getJSONObject(i);

                String id = tv.getString("id");
                String title = tv.getString("title");
                String description = tv.getString("description");
                String date = tv.getString("date");
                String imagePath = tv.getString("imagePath");

                TvResponse tvResponse = new TvResponse(id, title, description, date, imagePath);
                list.add(tvResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TvResponse> loadTv(String courseId) {
        String fileName = String.format("Module_%s.json", courseId);
        ArrayList<TvResponse> list = new ArrayList<>();
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


                    TvResponse courseResponse = new TvResponse(id, title, description, date, imagePath);
                    list.add(courseResponse);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }



}

