package com.kodingan.moviecatalog.ui.favtv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;


public class FavTvViewModel extends ViewModel {
    private CatalogRepository tvRepository;

    public FavTvViewModel(CatalogRepository mTvRepository) {
        this.tvRepository = mTvRepository;
    }

    public LiveData<PagedList<TvEntity>> getBookmarks() {
        return tvRepository.getFavoriteTvs();
    }

    void setBookmark(TvEntity tvEntity) {
        final boolean newState = !tvEntity.isBookmarked();
        tvRepository.setTvFavorite(tvEntity, newState);
    }
}

