package com.kodingan.moviecatalog.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kodingan.moviecatalog.R;
import com.kodingan.moviecatalog.ui.movie.MovieFragment;
import com.kodingan.moviecatalog.ui.favmovie.FavMovieFragment;
import com.kodingan.moviecatalog.ui.favtv.FavTvFragment;

import com.kodingan.moviecatalog.ui.tv.TvFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movie, R.string.tv_show,R.string.fav_movie,R.string.fav_tv_show};
    private final Context mContext;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieFragment();
            case 1:
                return new TvFragment();
            case 2:
                return new FavMovieFragment();
            case 3:
                return new FavTvFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 4;
    }
}