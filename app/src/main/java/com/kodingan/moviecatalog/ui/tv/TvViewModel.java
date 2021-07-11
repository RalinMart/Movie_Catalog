package com.kodingan.moviecatalog.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.vo.Resource;


public class TvViewModel extends ViewModel {
    private CatalogRepository academyRepository;

    public TvViewModel(CatalogRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<Resource<PagedList<TvEntity>>> getAllTvs() {
        return academyRepository.getAllTvs();
    }
}


