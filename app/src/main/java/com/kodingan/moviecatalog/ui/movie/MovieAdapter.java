package com.kodingan.moviecatalog.ui.movie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kodingan.moviecatalog.R;
import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.ui.detail.DetailMovieActivity;


public class MovieAdapter extends PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder> {

    private final MovieFragmentCallback callback;

    MovieAdapter(MovieFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId().equals(newItem.getMovieId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

//    hapus kode di bawah ini
//    private List<MovieEntity> listCourses = new ArrayList<>();
//
//    void setCourses(List<MovieEntity> listCourses) {
//        if (listCourses == null) return;
//        this.listCourses.clear();
//        this.listCourses.addAll(listCourses);
//    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        }
    }

//    @Override
//    public int getItemCount() {
//        return listCourses.size();
//    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;
        final ImageButton imgShare;

        MovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
        }

        void bind(MovieEntity movie) {
            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, movie.getRelease()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.getMovieId());
                itemView.getContext().startActivity(intent);
            });
            imgShare.setOnClickListener(v -> callback.onShareClick(movie));
            Glide.with(itemView.getContext())
                    .load(movie.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}

