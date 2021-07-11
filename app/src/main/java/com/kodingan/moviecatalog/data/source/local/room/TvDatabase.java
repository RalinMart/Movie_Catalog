package com.kodingan.moviecatalog.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;

@Database(entities = {TvEntity.class},
        version = 1,
        exportSchema = false)
public abstract class TvDatabase extends RoomDatabase {
    public abstract TvDao tvDao();

    private static volatile TvDatabase INSTANCE;

    public static TvDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TvDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TvDatabase.class, "Tvs.db")
                            .build();
                }

            }
        }
        return INSTANCE;
    }
}