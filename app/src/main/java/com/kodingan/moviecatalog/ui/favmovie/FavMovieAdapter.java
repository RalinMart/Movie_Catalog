package com.kodingan.moviecatalog.ui.favmovie;

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

public class FavMovieAdapter extends PagedListAdapter<MovieEntity, FavMovieAdapter.CourseViewHolder> {
    private final FavMovieFragmentCallback callback;

    FavMovieAdapter(FavMovieFragmentCallback callback) {
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
//    private ArrayList<MovieEntity> listCourses = new ArrayList<>();
//    public void setCourses(List<MovieEntity> movies) {
//        if (movies == null) return;
//        this.listCourses.clear();
//        this.listCourses.addAll(movies);
//    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_favmovie, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        MovieEntity course = getItem(position);
        if (course != null) {
            holder.bind(course);
        }
    }

    public MovieEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }

//    @Override
//    public int getItemCount() {
//        return listCourses.size();
//    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        CourseViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.img_poster);
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

