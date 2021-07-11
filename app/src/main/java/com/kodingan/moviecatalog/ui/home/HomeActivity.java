package com.kodingan.moviecatalog.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kodingan.moviecatalog.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.ic_baseline_movie_24,
            R.drawable.ic_baseline_live_tv_24,
            R.drawable.ic_baseline_favorite_border_24,
            R.drawable.ic_baseline_bookmark_border_24

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        setupTabIcons();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    private void setupTabIcons() {
        TabLayout tabs = findViewById(R.id.tabs);
        Objects.requireNonNull(tabs.getTabAt(0)). setIcon(tabIcons[0]);
        Objects.requireNonNull(tabs.getTabAt(1)).setIcon(tabIcons[1]);
        Objects.requireNonNull(tabs.getTabAt(2)). setIcon(tabIcons[2]);
        Objects.requireNonNull(tabs.getTabAt(3)).setIcon(tabIcons[3]);

    }
}