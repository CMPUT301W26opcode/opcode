package com.example.opcodeapp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testCreateEvent() {
        onView(withId(R.id.events_create_button)).perform(click());
        onView(withId(R.id.event_creator_name_input)).perform(ViewActions.typeText("Test Event"));
        onView(withId(R.id.event_creator_location_input)).perform(ViewActions.typeText("Test Location"));
        onView(withId(R.id.event_creator_description_input)).perform(ViewActions.typeText("Test Description"));
        onView(withId(R.id.event_creator_registration_start_input)).perform(ViewActions.typeText("19/03/2026"));
        onView(withId(R.id.event_creator_registration_end_input)).perform(ViewActions.typeText("20/05/2026"));
        onView(withId(R.id.event_creator_start_input)).perform(ViewActions.typeText("21/05/2026"));
        onView(withId(R.id.event_creator_end_input)).perform(ViewActions.typeText("22/05/2026"));
        onView(withId(R.id.event_creator_submit_btn)).perform(click());
        onView(withId(R.id.event_back_button)).perform(click());
        onView(withText("Test Event")).check(matches(isDisplayed()));

    }

    @Test
    public void testEnrolledUsers() {

        onView(withText("Test Event")).perform(click());
        onView(withId(R.id.enrolled_users_button)).perform(click());
        onView(withId(R.id.enrolled_users_list_view)).check(matches(isDisplayed()));

    }

    @Test
    public void testInvitedUsers() {

        onView(withText("Test Event")).perform(click());
        onView(withId(R.id.invited_users_button)).perform(click());
        onView(withId(R.id.invited_users_list_view)).check(matches(isDisplayed()));

    }

    @Test
    public void testApplicantUsers() {

        onView(withText("Test Event")).perform(click());
        onView(withId(R.id.all_applicants_button)).perform(click());
        onView(withId(R.id.waitlist_list_view)).check(matches(isDisplayed()));

    }









}
