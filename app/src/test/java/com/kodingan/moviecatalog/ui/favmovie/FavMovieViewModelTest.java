package com.kodingan.moviecatalog.ui.favmovie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;

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
public class FavMovieViewModelTest {

    private FavMovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository movieRepository;

    @Mock
    private Observer<PagedList<MovieEntity>> observer;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new FavMovieViewModel(movieRepository);
    }

    @Test
    public void getFavoriteMovies() {
        PagedList<MovieEntity> dummyMovies = pagedList;
        when(dummyMovies.size()).thenReturn(10);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(movieRepository.getFavoriteMovies()).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.getBookmarks().getValue();
        verify(movieRepository).getFavoriteMovies();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());

        viewModel.getBookmarks().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}