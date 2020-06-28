package com.example.carwalecovidtracker;


import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.carwalecovidtracker.TestHelper.getText;
import static com.example.carwalecovidtracker.TestHelper.greaterThanTheNumber;
import static com.example.carwalecovidtracker.TestHelper.lessThanTheNumber;

import static com.example.carwalecovidtracker.TestHelper.withRecyclerView;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;



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
    public void checkif_first_row_country_is_india() {
        // Step-1: Click Sort button

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.countryName))
                .check(matches(withText("India")));

    }

    @Test
    public void checkif_sort_ascending_is_working() throws InterruptedException {
        // Step-1: Click Sort button

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(Constant.SORT)).perform(click());

        //Enter values in spinners and click sort
        onView(withId(R.id.sortListField)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_COLUMN_TOTAL_CASES))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.sortRangeType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_TYPE_ASC))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.sortListButton)).perform(click());

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.totalCases))
                .check(matches(lessThanTheNumber(Integer.parseInt(getText(withRecyclerView(R.id.recyclerView)
                        .atPositionOnView(1, R.id.totalCases))))));

    }

    @Test
    public void checkif_sort_descending_is_working() throws InterruptedException {
        // Step-1: Click Sort button

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(Constant.SORT)).perform(click());

        //Enter values in spinners and click sort
        onView(withId(R.id.sortListField)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_COLUMN_TOTAL_CASES))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.sortRangeType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_TYPE_DESC))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.sortListButton)).perform(click());

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.totalCases))
                .check(matches(greaterThanTheNumber(Integer.parseInt(getText(withRecyclerView(R.id.recyclerView)
                        .atPositionOnView(1, R.id.totalCases))))));

    }

    @Test
    public void checkif_lessthan_condition_in_filter_is_working() throws InterruptedException {
        // Step-1: Click Sort button

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(Constant.FILTER)).perform(click());

        onView(withId(R.id.filterValue)).perform(typeText("500"), closeSoftKeyboard());

        //Enter values in spinners and click sort
        onView(withId(R.id.filterListField)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_COLUMN_DEATHS))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.filterRangeType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.FILTER_TYPE_LESS))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.filterListButton)).perform(click());

//        Thread.sleep(5000);

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.deaths))
                .check(matches(lessThanTheNumber(500)));

    }


    @Test
    public void checkif_greater_than_condition_in_filter_is_working() {
        // Step-1: Click Sort button

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(Constant.FILTER)).perform(click());

        onView(withId(R.id.filterValue)).perform(typeText("500"), closeSoftKeyboard());

        //Enter values in spinners and click sort
        onView(withId(R.id.filterListField)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.SORT_COLUMN_DEATHS))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.filterRangeType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(Constant.FILTER_TYPE_GRT))).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.filterListButton)).perform(click());

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.deaths))
                .check(matches(greaterThanTheNumber(500)));

    }

}