package com.kodingan.moviecatalog.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.utils.TvDataDummy;
import com.kodingan.moviecatalog.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailTvViewModelTest {

    private DetailTvViewModel viewModel;
    private TvEntity dummyTv = TvDataDummy.generateDummyTvs().get(0);
    private String tvId = dummyTv.getTvId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository tvRepository;

    @Mock
    private Observer<Resource<TvWithId>> observer;

    @Before
    public void setUp() {
        viewModel = new DetailTvViewModel(tvRepository);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getTvWithId() {
        Resource<TvWithId> dummyTvWithId = Resource.success(TvDataDummy.generateDummyTvWithId(dummyTv, true));
        MutableLiveData<Resource<TvWithId>> tv = new MutableLiveData<>();
        tv.setValue(dummyTvWithId);

        when(tvRepository.getTvWithId(tvId)).thenReturn(tv);

        viewModel.tvWithId.observeForever(observer);

        verify(observer).onChanged(dummyTvWithId);
    }
}