package com.kodingan.moviecatalog.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kodingan.moviecatalog.data.source.remote.response.MovieResponse;

import com.kodingan.moviecatalog.utils.EspressoIdlingResource;
import com.kodingan.moviecatalog.utils.MovieJsonHelper;

import java.util.List;

public class RemoteDataSourceMovie {

    private static RemoteDataSourceMovie INSTANCE;
    private MovieJsonHelper movieJsonHelper;
    private Handler handler = new Handler();
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSourceMovie(MovieJsonHelper movieJsonHelper) {
        this.movieJsonHelper = movieJsonHelper;
    }

    public static RemoteDataSourceMovie getInstance(MovieJsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceMovie(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> resultMovie = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultMovie.setValue(ApiResponse.success(movieJsonHelper.loadMovies()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovie;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getMovieWithId(String movieId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> resultMovies = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultMovies.setValue(ApiResponse.success(movieJsonHelper.loadMovie(movieId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovies;
    }


}

