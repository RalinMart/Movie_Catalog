package com.kodingan.moviecatalog.ui.favmovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;


public class FavMovieViewModel extends ViewModel {
    private CatalogRepository movieRepository;

    public FavMovieViewModel(CatalogRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<PagedList<MovieEntity>> getBookmarks() {
        return movieRepository.getFavoriteMovies();
    }

    void setBookmark(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isBookmarked();
        movieRepository.setFavoriteMovie(movieEntity, newState);
    }
}

