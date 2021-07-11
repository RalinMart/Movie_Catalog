package com.kodingan.moviecatalog.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movieentities")
public class MovieEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    private String movieId;

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

    public MovieEntity(@NonNull String movieId, String title, String description, String release, Boolean bookmarked, String imagePath) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.release = release;
        if (bookmarked != null) {
            this.bookmarked = bookmarked;
        }
        this.imagePath = imagePath;
    }

    @NonNull public String getMovieId() {
        return movieId;
    }

    public void setMovieId( String mCourseId) {
        this.movieId = mCourseId;
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

    public void setRelease(String mDeadline) {
        this.release = mDeadline;
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
