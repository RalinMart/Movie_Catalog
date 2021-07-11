package com.kodingan.moviecatalog.ui.home;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.utils.EspressoIdlingResource;
import com.kodingan.moviecatalog.utils.MovieDataDummy;
import com.kodingan.moviecatalog.utils.TvDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import com.kodingan.moviecatalog.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {
    private ArrayList<MovieEntity> dummyMovie = MovieDataDummy.generateDummyMovies();
    private ArrayList<TvEntity> dummyTv = TvDataDummy.generateDummyTvs();

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Release %s", dummyMovie.get(0).getRelease()))));
    }



    @Test
    public void loadTv() {
        onView(withText("TV Show")).perform(click());
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition(dummyTv.size()));

    }

    @Test
    public void loadDetailTv() {
        onView(withText("TV Show")).perform(click());
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyTv.get(0).getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Release %s", dummyTv.get(0).getRelease()))));
    }

    @Test
    public void loadFavoriteMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withText("Fav Movie")).perform(click());
        onView(withId(R.id.rv_favmovie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favmovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void loadFavoriteTvs() {
        onView(withText("TV Show")).perform(click());
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withText("Fav TV")).perform(click());
        onView(withId(R.id.rv_favtv)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_favtv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}