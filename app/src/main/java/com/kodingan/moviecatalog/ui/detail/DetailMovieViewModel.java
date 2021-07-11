package com.kodingan.moviecatalog.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.vo.Resource;

public class DetailMovieViewModel extends ViewModel {
    private MutableLiveData<String> movieId = new MutableLiveData<>();
    private CatalogRepository movieRepository;

    public DetailMovieViewModel(CatalogRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<MovieWithId>> movieWithId = Transformations.switchMap(movieId,
            mCourseId -> movieRepository.getMovieWithId(mCourseId));

    public String getMovieId() {
        return movieId.getValue();
    }

    public void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    void setBookmark() {
        Resource<MovieWithId> movieResource = movieWithId.getValue();
        if (movieResource != null) {
            MovieWithId movieWithId = movieResource.data;

            if (movieWithId != null) {
                MovieEntity movieEntity = movieWithId.mMovie;

                // Kode di bawah menggunakan tanda seru (!),
                // karena akan mengganti status dari apakah sudah di favmovie atau tidak menjadi apakah sudah siap dibookmark atau tidak
                final boolean newState = !movieEntity.isBookmarked();
                movieRepository.setFavoriteMovie(movieEntity, newState);
            }
        }
    }
}


