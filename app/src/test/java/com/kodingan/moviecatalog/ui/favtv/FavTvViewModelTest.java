package com.kodingan.moviecatalog.ui.favtv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavTvViewModelTest {
    private FavTvViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository tvRepository;

    @Mock
    private Observer<PagedList<TvEntity>> observer;

    @Mock
    private PagedList<TvEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new FavTvViewModel(tvRepository);
    }

    @Test
    public void getFavoriteTvs() {
        PagedList<TvEntity> dummyTvs = pagedList;
        when(dummyTvs.size()).thenReturn(10);
        MutableLiveData<PagedList<TvEntity>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTvs);

        when(tvRepository.getFavoriteTvs()).thenReturn(tvs);
        List<TvEntity> tvEntities = viewModel.getBookmarks().getValue();
        verify(tvRepository).getFavoriteTvs();
        assertNotNull(tvEntities);
        assertEquals(10, tvEntities.size());

        viewModel.getBookmarks().observeForever(observer);
        verify(observer).onChanged(dummyTvs);
    }
}