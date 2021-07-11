package com.kodingan.moviecatalog.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.source.local.LocalDataSourceMovie;
import com.kodingan.moviecatalog.data.source.local.LocalDataSourceTv;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceMovie;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceTv;
import com.kodingan.moviecatalog.data.source.remote.response.MovieResponse;
import com.kodingan.moviecatalog.data.source.remote.response.TvResponse;
import com.kodingan.moviecatalog.utils.AppExecutors;
import com.kodingan.moviecatalog.utils.LiveDataTestUtil;
import com.kodingan.moviecatalog.utils.MovieDataDummy;
import com.kodingan.moviecatalog.utils.PagedListUtil;
import com.kodingan.moviecatalog.utils.TvDataDummy;
import com.kodingan.moviecatalog.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class CatalogRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSourceMovie remoteDataMovie = Mockito.mock(RemoteDataSourceMovie.class);
    private LocalDataSourceMovie localDataMovie = Mockito.mock(LocalDataSourceMovie.class);
    private RemoteDataSourceTv remoteDataTv = Mockito.mock(RemoteDataSourceTv.class);
    private LocalDataSourceTv localDataTv = Mockito.mock(LocalDataSourceTv.class);
    private AppExecutors appExecutors = Mockito.mock(AppExecutors.class);

    private FakeCatalogRepository catalogRepository = new FakeCatalogRepository(remoteDataMovie, localDataMovie, remoteDataTv, localDataTv,appExecutors);

    private ArrayList<MovieResponse> movieResponses = MovieDataDummy.generateRemoteDummyMovies();
    private String movieId = movieResponses.get(0).getId();
    private ArrayList<TvResponse> tvResponses = TvDataDummy.generateRemoteDummyTvs();
    private String tvId = tvResponses.get(0).getId();


    @Test
    public void getAllMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataMovie.getAllMovies()).thenReturn(dataSourceFactory);
        catalogRepository.getAllMovies();
        Resource<PagedList<MovieEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(MovieDataDummy.generateDummyMovies()));
        verify(localDataMovie).getAllMovies();
        assertNotNull(movieEntities.data);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }

    @Test
    public void getMovieWithId() {
        MutableLiveData<MovieWithId> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(MovieDataDummy.generateDummyMovieWithId(MovieDataDummy.generateDummyMovies().get(0), false));
        when(localDataMovie.getMovieWithId(movieId)).thenReturn(dummyEntity);

        Resource<MovieWithId> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieWithId(movieId));
        verify(localDataMovie).getMovieWithId(movieId);
        assertNotNull(movieEntities.data);
        assertNotNull(movieEntities.data.mMovie.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), movieEntities.data.mMovie.getTitle());
    }

    @Test
    public void getFavoriteMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataMovie.getFavoriteMovies()).thenReturn(dataSourceFactory);
        catalogRepository.getFavoriteMovies();

        Resource<PagedList<MovieEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(MovieDataDummy.generateDummyMovies()));
        verify(localDataMovie).getFavoriteMovies();


        assertEquals(movieResponses.size(), movieEntities.data.size());
    }



    @Test
    public void getAllTvs() {
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataTv.getAllTvs()).thenReturn(dataSourceFactory);
        catalogRepository.getAllTvs();
        Resource<PagedList<TvEntity>> tvEntities = Resource.success(PagedListUtil.mockPagedList(TvDataDummy.generateDummyTvs()));
        verify(localDataTv).getAllTvs();
        assertNotNull(tvEntities.data);
        assertEquals(tvResponses.size(), tvEntities.data.size());
    }

    @Test
    public void getTvWithId() {
        MutableLiveData<TvWithId> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(TvDataDummy.generateDummyTvWithId(TvDataDummy.generateDummyTvs().get(0), false));
        when(localDataTv.getTvWithId(tvId)).thenReturn(dummyEntity);

        Resource<TvWithId> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTvWithId(tvId));
        verify(localDataTv).getTvWithId(tvId);
        assertNotNull(tvEntities.data);
        assertNotNull(tvEntities.data.mTv.getTitle());
        assertEquals(tvResponses.get(0).getTitle(), tvEntities.data.mTv.getTitle());
    }

    @Test
    public void getFavoriteTvs() {
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataTv.getFavoriteTvs()).thenReturn(dataSourceFactory);
        catalogRepository.getFavoriteTvs();

        Resource<PagedList<TvEntity>> tvEntities = Resource.success(PagedListUtil.mockPagedList(TvDataDummy.generateDummyTvs()));
        verify(localDataTv).getFavoriteTvs();
        assertNotNull(tvEntities);

        assertEquals(tvResponses.size(), tvEntities.data.size());
    }


}