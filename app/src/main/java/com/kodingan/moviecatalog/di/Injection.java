package com.kodingan.moviecatalog.di;

import android.content.Context;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.LocalDataSourceMovie;
import com.kodingan.moviecatalog.data.source.local.LocalDataSourceTv;
import com.kodingan.moviecatalog.data.source.local.room.MovieDatabase;
import com.kodingan.moviecatalog.data.source.local.room.TvDatabase;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceMovie;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceTv;
import com.kodingan.moviecatalog.utils.AppExecutors;
import com.kodingan.moviecatalog.utils.MovieJsonHelper;
import com.kodingan.moviecatalog.utils.TvJsonHelper;

public class Injection {

    public static CatalogRepository provideRepository(Context context) {

        MovieDatabase databaseMovie = MovieDatabase.getInstance(context);

        RemoteDataSourceMovie remoteDataSourceMovie = RemoteDataSourceMovie.getInstance(new MovieJsonHelper(context));
        LocalDataSourceMovie localDataSourceMovie = LocalDataSourceMovie.getInstance(databaseMovie.movieDao());

        TvDatabase databaseTv = TvDatabase.getInstance(context);

        RemoteDataSourceTv remoteDataSourceTv = RemoteDataSourceTv.getInstance(new TvJsonHelper(context));
        LocalDataSourceTv localDataSourceTv = LocalDataSourceTv.getInstance(databaseTv.tvDao());
        AppExecutors appExecutors = new AppExecutors();

        return CatalogRepository.getInstance(remoteDataSourceMovie, localDataSourceMovie,remoteDataSourceTv, localDataSourceTv, appExecutors);
    }
}
