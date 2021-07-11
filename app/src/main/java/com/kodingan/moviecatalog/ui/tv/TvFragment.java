package com.kodingan.moviecatalog.ui.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kodingan.moviecatalog.R;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;

import com.kodingan.moviecatalog.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements TvFragmentCallback {
    private RecyclerView rvTv;
    private ProgressBar progressBar;

    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTv = view.findViewById(R.id.rv_tv);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            TvViewModel viewModel = new ViewModelProvider(this, factory).get(TvViewModel.class);

            TvAdapter tvAdapter = new TvAdapter(this);
            viewModel.getAllTvs().observe(this, movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvAdapter.submitList(movies.data);
                            tvAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });

            rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTv.setHasFixedSize(true);
            rvTv.setAdapter(tvAdapter);
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
}

