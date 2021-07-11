package com.kodingan.moviecatalog.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.vo.Resource;

public interface CatalogDataSource {

    LiveData<Resource<PagedList<MovieEntity>>> getAllMovies();

    LiveData<Resource<MovieWithId>> getMovieWithId(String movieId);

    LiveData<PagedList<MovieEntity>> getFavoriteMovies();

    void setFavoriteMovie(MovieEntity movie, boolean state);


    LiveData<Resource<PagedList<TvEntity>>> getAllTvs();

    LiveData<Resource<TvWithId>> getTvWithId(String tvId);

    LiveData<PagedList<TvEntity>> getFavoriteTvs();

    void setTvFavorite(TvEntity tv, boolean state);


}
