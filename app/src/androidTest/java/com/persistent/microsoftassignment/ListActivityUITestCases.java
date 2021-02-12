package com.persistent.microsoftassignment;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.persistent.microsoftassignment.ui.main.MainActivity;
import com.persistent.microsoftassignment.ui.main.videos.VideosFragment;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListActivityUITestCases {

    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);

    @Test
    public void testSampleRecyclerVisible() {
        activityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                videoFragment();
            }
        });

        Espresso.onView(ViewMatchers.withId(R.id.movieRecyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewData(){
        if (getRecyclerViewCount() > 0){
            Espresso.onView(ViewMatchers.withId(R.id.movieRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }

    private int getRecyclerViewCount(){
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.movieRecyclerView);
        return recyclerView.getAdapter().getItemCount();
    }

    private void videoFragment() {
        MainActivity activity = activityRule.getActivity();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        VideosFragment voiceFragment = new VideosFragment();
        transaction.add(voiceFragment, "videoFragment");
        transaction.commit();
    }
}
