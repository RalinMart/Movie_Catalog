package com.kodingan.moviecatalog.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kodingan.moviecatalog.data.source.remote.response.TvResponse;
import com.kodingan.moviecatalog.utils.EspressoIdlingResource;
import com.kodingan.moviecatalog.utils.TvJsonHelper;

import java.util.List;

public class RemoteDataSourceTv {

    private static RemoteDataSourceTv INSTANCE;
    private TvJsonHelper tvJsonHelper;
    private Handler handler = new Handler();
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSourceTv(TvJsonHelper tvJsonHelper) {
        this.tvJsonHelper = tvJsonHelper;
    }

    public static RemoteDataSourceTv getInstance(TvJsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceTv(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<TvResponse>>> getAllTvs() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvResponse>>> resultTv = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultTv.setValue(ApiResponse.success(tvJsonHelper.loadTvs()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTv;
    }





    public LiveData<ApiResponse<List<TvResponse>>> getTvWithId(String tvId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvResponse>>> resultTvs = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultTvs.setValue(ApiResponse.success(tvJsonHelper.loadTv(tvId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTvs;
    }
}

