package com.kodingan.moviecatalog.ui.favtv;


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
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavTvFragment extends Fragment implements FavTvFragmentCallback {
    private RecyclerView rvFavTv;
    private ProgressBar progressBar;
    private FavTvViewModel viewModel;
    private FavTvAdapter adapter;

    public FavTvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favtv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavTv = view.findViewById(R.id.rv_favtv);
        progressBar = view.findViewById(R.id.progress_bar);
        itemTouchHelper.attachToRecyclerView(rvFavTv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(FavTvViewModel.class);

            adapter = new FavTvAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(this, courses -> {
                progressBar.setVisibility(View.GONE);
                adapter.submitList(courses);
                adapter.notifyDataSetChanged();
            });

            rvFavTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavTv.setHasFixedSize(true);
            rvFavTv.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(TvEntity tv) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan acara ini sekarang.")
                    .setText(String.format("Info selengkapnya tentang  %s di tmdb.com", tv.getTitle()))
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
                TvEntity tvEntity = adapter.getSwipedData(swipedPosition);
                viewModel.setBookmark(tvEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setBookmark(tvEntity));
                snackbar.show();
            }
        }
    });
}

