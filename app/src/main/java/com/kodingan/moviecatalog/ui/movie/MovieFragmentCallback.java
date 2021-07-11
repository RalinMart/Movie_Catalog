package com.kodingan.moviecatalog.ui.movie;

import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;

public interface MovieFragmentCallback {
    void onShareClick(MovieEntity movie);
}
