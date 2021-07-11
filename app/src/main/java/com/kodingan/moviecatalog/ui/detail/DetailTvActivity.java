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
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.viewmodel.ViewModelFactory;


public class DetailTvActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";

    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    DetailTvViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
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
        viewModel = new ViewModelProvider(this, factory).get(DetailTvViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String tvId = extras.getString(EXTRA_TV);
            if (tvId != null) {
                viewModel.setTvId(tvId);

                viewModel.tvWithId.observe(this, tvWithResource -> {
                    if (tvWithResource != null) {

                        switch (tvWithResource.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (tvWithResource.data != null) {
                                    progressBar.setVisibility(View.GONE);

                                    populateTv(tvWithResource.data.mTv);
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

    private void populateTv(TvEntity tvEntity) {
        textTitle.setText(tvEntity.getTitle());
         textDesc.setText(tvEntity.getDescription());
        textDate.setText(String.format("Release %s", tvEntity.getRelease()));

        Glide.with(this)
                .load(tvEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_v, menu);
        this.menu = menu;
        viewModel.tvWithId.observe(this, movieWithId -> {
            if (movieWithId != null) {
                switch (movieWithId.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (movieWithId.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean state = movieWithId.data.mTv.isBookmarked();
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
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white));
        }
    }
}


