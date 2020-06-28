package com.example.carwalecovidtracker;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;

class NumberLessThanMatcher extends BoundedMatcher<View, TextView> {


    private final int no;
    NumberLessThanMatcher(int number) {
        super(TextView.class);
        this.no = number;
    }
    @Override protected boolean matchesSafely(TextView item) {
        return Integer.parseInt(item.getText().toString()) < this.no ;
    }
    @Override public void describeTo(Description description) {
        description.appendText("Did not match with:").appendValue(String.valueOf(this.no));
    }
}

