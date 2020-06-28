package com.example.carwalecovidtracker;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TAG = "EpisodeActivityScreenTest";
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class) {

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
        }

    };

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void checkif_correct_episode_launched() {
        // Step-1: Click 'See Photos' button
//
//        onView(withId(R.id.learnByPractice)).perform(click());
//        onView(withId(R.id.rv_parent)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
//        // Checks that the GalleryActivity opens with the correct listing name displayed
//        onView(withId(R.id.gridViewRV)).check((ViewAssertion) isDisplayed());
    }

}