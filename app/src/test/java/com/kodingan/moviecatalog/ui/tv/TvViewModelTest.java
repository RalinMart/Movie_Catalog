package com.kodingan.moviecatalog.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TvViewModelTest {

    private TvViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository tvRepository;

    @Mock
    private Observer<Resource<PagedList<TvEntity>>> observer;

    @Mock
    private PagedList<TvEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new TvViewModel(tvRepository);
    }

    @Test
    public void getTvs() {
        Resource<PagedList<TvEntity>> dummyTvs = Resource.success(pagedList);
        when(dummyTvs.data.size()).thenReturn(10);
        MutableLiveData<Resource<PagedList<TvEntity>>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTvs);

        when(tvRepository.getAllTvs()).thenReturn(tvs);
        List<TvEntity> tvEntities = viewModel.getAllTvs().getValue().data;
        verify(tvRepository).getAllTvs();
        assertNotNull(tvEntities);
        assertEquals(10, tvEntities.size());

        viewModel.getAllTvs().observeForever(observer);
        verify(observer).onChanged(dummyTvs);
    }

}