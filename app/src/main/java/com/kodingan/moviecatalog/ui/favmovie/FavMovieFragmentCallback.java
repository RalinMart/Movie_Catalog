package com.kodingan.moviecatalog.ui.favmovie;


import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;

interface FavMovieFragmentCallback {
    void onShareClick(MovieEntity movie);
}

