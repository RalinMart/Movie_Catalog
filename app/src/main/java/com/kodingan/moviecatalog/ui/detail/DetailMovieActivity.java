package com.kodingan.moviecatalog.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kodingan.moviecatalog.R;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.viewmodel.ViewModelFactory;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    DetailMovieViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);

        imagePoster = findViewById(R.id.image_poster);
        progressBar = findViewById(R.id.progress_bar);


        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(this, factory).get(DetailMovieViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieId = extras.getString(EXTRA_MOVIE);
            if (movieId != null) {
                viewModel.setMovieId(movieId);

                viewModel.movieWithId.observe(this, movieWithResource -> {
                    if (movieWithResource != null) {

                        switch (movieWithResource.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (movieWithResource.data != null) {
                                    progressBar.setVisibility(View.GONE);

                                    populateMovie(movieWithResource.data.mMovie);
                                }
                                break;
                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
            }
        }




    }

    private void populateMovie(MovieEntity movieEntity) {
        textTitle.setText(movieEntity.getTitle());
         textDesc.setText(movieEntity.getDescription());
        textDate.setText(String.format("Release %s", movieEntity.getRelease()));

        Glide.with(this)
                .load(movieEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        viewModel.movieWithId.observe(this, movieWithId -> {
            if (movieWithId != null) {
                switch (movieWithId.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (movieWithId.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean state = movieWithId.data.mMovie.isBookmarked();
                            setBookmarkState(state);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark) {
            viewModel.setBookmark();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24));
        }
    }
}


