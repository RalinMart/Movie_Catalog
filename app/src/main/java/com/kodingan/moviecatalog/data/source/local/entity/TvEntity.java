package com.kodingan.moviecatalog.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tventities")
public class TvEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    private String tvId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "release")
    private String release;

    @ColumnInfo(name = "bookmarked")
    private boolean bookmarked = false;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    public TvEntity(String tvId, String title, String description, String release, Boolean bookmarked, String imagePath) {
        this.tvId = tvId;
        this.title = title;
        this.description = description;
        this.release = release;
        if (bookmarked != null) {
            this.bookmarked = bookmarked;
        }
        this.imagePath = imagePath;
    }

    public String getTvId() {
        return tvId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public String getRelease() {
        return release;
    }



    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean mBookmarked) {
        this.bookmarked = mBookmarked;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String mImagePath) {
        this.imagePath = mImagePath;
    }
}
