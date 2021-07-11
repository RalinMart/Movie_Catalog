package com.kodingan.moviecatalog.data.source.local.entity;

import androidx.room.Embedded;


public class MovieWithId {
    @Embedded
    public MovieEntity mMovie;


}