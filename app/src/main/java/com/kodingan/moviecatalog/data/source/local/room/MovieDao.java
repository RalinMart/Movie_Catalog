package com.kodingan.moviecatalog.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;


import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movieentities")
    DataSource.Factory<Integer, MovieEntity> getMovies();

    @Query("SELECT * FROM movieentities where bookmarked = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovie();

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    LiveData<MovieWithId> getMovieById(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movies);

    @Update
    void updateMovie(MovieEntity movie);









}
