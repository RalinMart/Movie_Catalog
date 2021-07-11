package com.kodingan.moviecatalog.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;

import com.kodingan.moviecatalog.data.source.local.room.MovieDao;

import java.util.List;

public class LocalDataSourceMovie {

    private static LocalDataSourceMovie INSTANCE;
    private final MovieDao mMovieDao;

    private LocalDataSourceMovie(MovieDao mMovieDao) {
        this.mMovieDao = mMovieDao;
    }

    public static LocalDataSourceMovie getInstance(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSourceMovie(movieDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, MovieEntity> getAllMovies() {
        return mMovieDao.getMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovies() {
        return mMovieDao.getFavoriteMovie();
    }

    public LiveData<MovieWithId> getMovieWithId(final String movieId) {
        return mMovieDao.getMovieById(movieId);
    }



    public void insertMovies(List<MovieEntity> movies) {
        mMovieDao.insertMovies(movies);
    }


    public void setFavoriteMovie(MovieEntity movie, boolean newState) {
        movie.setBookmarked(newState);
        mMovieDao.updateMovie(movie);
    }






}