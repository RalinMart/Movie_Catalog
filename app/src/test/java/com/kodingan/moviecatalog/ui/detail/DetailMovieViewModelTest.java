package com.kodingan.moviecatalog.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kodingan.moviecatalog.data.CatalogRepository;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.utils.MovieDataDummy;
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
public class DetailMovieViewModelTest {

    private DetailMovieViewModel viewModel;
    private MovieEntity dummyMovie = MovieDataDummy.generateDummyMovies().get(0);
    private String movieId = dummyMovie.getMovieId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository movieRepository;

    @Mock
    private Observer<Resource<MovieWithId>> observer;

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(movieRepository);
        viewModel.setMovieId(movieId);
    }

    @Test
    public void getMovieWithId() {
        Resource<MovieWithId> dummyMovieWithId = Resource.success(MovieDataDummy.generateDummyMovieWithId(dummyMovie, true));
        MutableLiveData<Resource<MovieWithId>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovieWithId);

        when(movieRepository.getMovieWithId(movieId)).thenReturn(movie);

        viewModel.movieWithId.observeForever(observer);

        verify(observer).onChanged(dummyMovieWithId);
    }
}