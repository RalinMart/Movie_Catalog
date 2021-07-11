package com.kodingan.moviecatalog.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.vo.Resource;

public class DetailTvViewModel extends ViewModel {
    private MutableLiveData<String> tvId = new MutableLiveData<>();
    private CatalogRepository tvRepository;

    public DetailTvViewModel(CatalogRepository mTvRepository) {
        this.tvRepository = mTvRepository;
    }

    public LiveData<Resource<TvWithId>> tvWithId = Transformations.switchMap(tvId,
            mTvId -> tvRepository.getTvWithId(mTvId));

    public String getTvId() {
        return tvId.getValue();
    }

    public void setTvId(String tvId) {
        this.tvId.setValue(tvId);
    }

    void setBookmark() {
        Resource<TvWithId> tvResource = tvWithId.getValue();
        if (tvResource != null) {
            TvWithId tvWithId = tvResource.data;

            if (tvWithId != null) {
                TvEntity tvEntity = tvWithId.mTv;

                // Kode di bawah menggunakan tanda seru (!),
                // karena akan mengganti status dari apakah sudah di favmovie atau tidak menjadi apakah sudah siap dibookmark atau tidak
                final boolean newState = !tvEntity.isBookmarked();
                tvRepository.setTvFavorite(tvEntity, newState);
            }
        }
    }
}


