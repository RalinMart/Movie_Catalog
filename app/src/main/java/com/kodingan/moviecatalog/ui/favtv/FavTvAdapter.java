package com.kodingan.moviecatalog.ui.favtv;

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
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.ui.detail.DetailTvActivity;

public class FavTvAdapter extends PagedListAdapter<TvEntity, FavTvAdapter.CourseViewHolder> {
    private final FavTvFragmentCallback callback;

    FavTvAdapter(FavTvFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.getTvId().equals(newItem.getTvId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

//    hapus kode di bawah ini
//    private ArrayList<TvEntity> listCourses = new ArrayList<>();
//    public void setCourses(List<TvEntity> movies) {
//        if (movies == null) return;
//        this.listCourses.clear();
//        this.listCourses.addAll(movies);
//    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_favtv, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        TvEntity course = getItem(position);
        if (course != null) {
            holder.bind(course);
        }
    }

    public TvEntity getSwipedData(int swipedPosition) {
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

        void bind(TvEntity course) {
            tvTitle.setText(course.getTitle());
            tvDescription.setText(course.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, course.getRelease()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.EXTRA_TV, course.getTvId());
                itemView.getContext().startActivity(intent);
            });
            imgShare.setOnClickListener(v -> callback.onShareClick(course));
            Glide.with(itemView.getContext())
                    .load(course.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}

