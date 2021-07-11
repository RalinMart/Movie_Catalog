package com.kodingan.moviecatalog.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.data.source.local.room.TvDao;

import java.util.List;

public class LocalDataSourceTv {

    private static LocalDataSourceTv INSTANCE;
    private final TvDao mTvDao;

    private LocalDataSourceTv(TvDao mTvDao) {
        this.mTvDao = mTvDao;
    }

    public static LocalDataSourceTv getInstance(TvDao tvDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSourceTv(tvDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, TvEntity> getAllTvs() {
        return mTvDao.getTvs();
    }

    public DataSource.Factory<Integer, TvEntity> getFavoriteTvs() {
        return mTvDao.getFavoriteTv();
    }

    public LiveData<TvWithId> getTvWithId(final String tvId) {
        return mTvDao.getTvWithId(tvId);
    }



    public void insertTvs(List<TvEntity> tvs) {
        mTvDao.insertTvs(tvs);
    }



    public void setFavoriteTv(TvEntity tv, boolean newState) {
        tv.setBookmarked(newState);
        mTvDao.updateTv(tv);
    }





}