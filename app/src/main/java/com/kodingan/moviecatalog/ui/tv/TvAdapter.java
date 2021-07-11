package com.kodingan.moviecatalog.ui.tv;

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


public class  TvAdapter extends PagedListAdapter<TvEntity, TvAdapter.TvViewHolder> {

    private final TvFragmentCallback callback;

    TvAdapter(TvFragmentCallback callback) {
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
//    private List<TvEntity> listCourses = new ArrayList<>();
//
//    void setCourses(List<TvEntity> listCourses) {
//        if (listCourses == null) return;
//        this.listCourses.clear();
//        this.listCourses.addAll(listCourses);
//    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        TvEntity tv = getItem(position);
        if (tv != null) {
            holder.bind(tv);
        }
    }

//    @Override
//    public int getItemCount() {
//        return listCourses.size();
//    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;
        final ImageButton imgShare;

        TvViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
        }

        void bind(TvEntity tv) {
            tvTitle.setText(tv.getTitle());
            tvDescription.setText(tv.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, tv.getRelease()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.EXTRA_TV, tv.getTvId());
                itemView.getContext().startActivity(intent);
            });
            imgShare.setOnClickListener(v -> callback.onShareClick(tv));
            Glide.with(itemView.getContext())
                    .load(tv.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}

