package com.kodingan.moviecatalog.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.di.Injection;

import com.kodingan.moviecatalog.ui.detail.DetailMovieViewModel;
import com.kodingan.moviecatalog.ui.detail.DetailTvViewModel;
import com.kodingan.moviecatalog.ui.favmovie.FavMovieViewModel;
import com.kodingan.moviecatalog.ui.favtv.FavTvViewModel;
import com.kodingan.moviecatalog.ui.movie.MovieViewModel;
import com.kodingan.moviecatalog.ui.tv.TvViewModel;

public class ViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final CatalogRepository mCatalogRepository;

    public ViewModelFactory(CatalogRepository mCatalogRepository) {
        this.mCatalogRepository = mCatalogRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(FavMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new FavMovieViewModel(mCatalogRepository);
        }
        if (modelClass.isAssignableFrom(TvViewModel.class)) {
            //noinspection unchecked
            return (T) new TvViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(DetailTvViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailTvViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(FavTvViewModel.class)) {
            //noinspection unchecked
            return (T) new FavTvViewModel(mCatalogRepository);

        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

