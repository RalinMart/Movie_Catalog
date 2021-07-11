package com.kodingan.moviecatalog.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.vo.Resource;


public class MovieViewModel extends ViewModel {
    private CatalogRepository movieRepository;

    public MovieViewModel(CatalogRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getMovies() {
        return movieRepository.getAllMovies();
    }
}


