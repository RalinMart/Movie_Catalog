package com.kodingan.moviecatalog.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kodingan.moviecatalog.data.source.local.LocalDataSourceMovie;
import com.kodingan.moviecatalog.data.source.local.LocalDataSourceTv;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.data.source.remote.ApiResponse;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceMovie;
import com.kodingan.moviecatalog.data.source.remote.RemoteDataSourceTv;
import com.kodingan.moviecatalog.data.source.remote.response.MovieResponse;
import com.kodingan.moviecatalog.data.source.remote.response.TvResponse;
import com.kodingan.moviecatalog.utils.AppExecutors;
import com.kodingan.moviecatalog.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeCatalogRepository implements CatalogDataSource {



    private final RemoteDataSourceMovie remoteDataSourceMovie;
    private final LocalDataSourceMovie localDataSourceMovie;
    private final RemoteDataSourceTv remoteDataSourceTv;
    private final LocalDataSourceTv localDataSourceTv;
    private final AppExecutors appExecutors;

    public FakeCatalogRepository(@NonNull RemoteDataSourceMovie remoteDataSourceMovie, @NonNull LocalDataSourceMovie localDataSourceMovie, @NonNull RemoteDataSourceTv remoteDataSourceTv, @NonNull LocalDataSourceTv localDataSourceTv, AppExecutors appExecutors) {
        this.remoteDataSourceMovie = remoteDataSourceMovie;
        this.localDataSourceMovie = localDataSourceMovie;
        this.remoteDataSourceTv = remoteDataSourceTv;
        this.localDataSourceTv = localDataSourceTv;
        this.appExecutors = appExecutors;
    }




    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getAllMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            public LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSourceMovie.getAllMovies(), config).build();
            }

            @Override
            public Boolean shouldFetch(PagedList<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            public LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteDataSourceMovie.getAllMovies();
            }

            @Override
            public void saveCallResult(List<MovieResponse> movieRespons) {
                ArrayList<MovieEntity> courseList = new ArrayList<>();
                for (MovieResponse response : movieRespons) {
                    MovieEntity course = new MovieEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());
                    courseList.add(course);
                }

                localDataSourceMovie.insertMovies(courseList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieWithId>> getMovieWithId(String movieId) {
        return new NetworkBoundResource<MovieWithId, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<MovieWithId> loadFromDB() {
                return localDataSourceMovie.getMovieWithId(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieWithId movieWithId) {
                return (movieWithId == null || movieWithId.mMovie == null) ;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteDataSourceMovie.getMovieWithId(movieId);
            }

            @Override
            protected void saveCallResult(List<MovieResponse> movieRespons) {

                ArrayList<MovieEntity> courseList = new ArrayList<>();
                for (MovieResponse response : movieRespons) {
                    MovieEntity course = new MovieEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());
                    courseList.add(course);
                }

                localDataSourceMovie.insertMovies(courseList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovies() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSourceMovie.getFavoriteMovies(), config).build();
    }

    @Override
    public void setFavoriteMovie(MovieEntity movie, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSourceMovie.setFavoriteMovie(movie, state));
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getAllTvs() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>(appExecutors) {
            @Override
            public LiveData<PagedList<TvEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSourceTv.getAllTvs(), config).build();
            }

            @Override
            public Boolean shouldFetch(PagedList<TvEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            public LiveData<ApiResponse<List<TvResponse>>> createCall() {
                return remoteDataSourceTv.getAllTvs();
            }

            @Override
            public void saveCallResult(List<TvResponse> tvResponses) {
                ArrayList<TvEntity> tvList = new ArrayList<>();
                for (TvResponse response : tvResponses) {
                    TvEntity tv = new TvEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());
                    tvList.add(tv);
                }

                localDataSourceTv.insertTvs(tvList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvWithId>> getTvWithId(String tvId) {
        return new NetworkBoundResource<TvWithId, List<TvResponse>>(appExecutors) {
            @Override
            protected LiveData<TvWithId> loadFromDB() {
                return localDataSourceTv.getTvWithId(tvId);
            }

            @Override
            protected Boolean shouldFetch(TvWithId tvWithId) {
                return (tvWithId == null || tvWithId.mTv == null) ;
            }

            @Override
            protected LiveData<ApiResponse<List<TvResponse>>> createCall() {
                return remoteDataSourceTv.getTvWithId(tvId);
            }

            @Override
            protected void saveCallResult(List<TvResponse> courseResponsVS) {

                ArrayList<TvEntity> courseList = new ArrayList<>();
                for (TvResponse response : courseResponsVS) {
                    TvEntity course = new TvEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());

                    courseList.add(course);
                }

                localDataSourceTv.insertTvs(courseList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<TvEntity>> getFavoriteTvs() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSourceTv.getFavoriteTvs(), config).build();
    }

    @Override
    public void setTvFavorite(TvEntity tv, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSourceTv.setFavoriteTv(tv, state));
    }
}
