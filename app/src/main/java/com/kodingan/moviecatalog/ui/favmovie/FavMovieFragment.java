package com.kodingan.moviecatalog.ui.favmovie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kodingan.moviecatalog.R;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.google.android.material.snackbar.Snackbar;
import com.kodingan.moviecatalog.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment implements FavMovieFragmentCallback {
    private RecyclerView rvFavMovie;
    private ProgressBar progressBar;
    private FavMovieViewModel viewModel;
    private FavMovieAdapter adapter;

    public FavMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favmovie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMovie = view.findViewById(R.id.rv_favmovie);
        progressBar = view.findViewById(R.id.progress_bar);
        itemTouchHelper.attachToRecyclerView(rvFavMovie);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(FavMovieViewModel.class);

            adapter = new FavMovieAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(this, courses -> {
                progressBar.setVisibility(View.GONE);
                adapter.submitList(courses);
                adapter.notifyDataSetChanged();
            });

            rvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavMovie.setHasFixedSize(true);
            rvFavMovie.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(MovieEntity movie) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan film ini sekarang.")
                    .setText(String.format("Info selengkapnya tentang %s di tmdb.com", movie.getTitle()))
                    .startChooser();
        }
    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                MovieEntity movieEntity = adapter.getSwipedData(swipedPosition);
                viewModel.setBookmark(movieEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setBookmark(movieEntity));
                snackbar.show();
            }
        }
    });
}

