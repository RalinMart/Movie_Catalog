package com.kodingan.moviecatalog.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;

import java.util.List;

@Dao
public interface TvDao {

    @Query("SELECT * FROM tventities")
    DataSource.Factory<Integer, TvEntity> getTvs();

    @Query("SELECT * FROM tventities where bookmarked = 1")
    DataSource.Factory<Integer, TvEntity> getFavoriteTv();

    @Transaction
    @Query("SELECT * FROM tventities WHERE tvId = :tvId")
    LiveData<TvWithId> getTvWithId(String tvId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvs(List<TvEntity> tvs);

    @Update
    void updateTv(TvEntity tv);










}
