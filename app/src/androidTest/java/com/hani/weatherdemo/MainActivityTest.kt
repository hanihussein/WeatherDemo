package com.hani.weatherdemo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hani.weatherdemo.core.utils.EspressoIdlingResource
import com.hani.weatherdemo.presentation.ForecastAdapter
import com.hani.weatherdemo.presentation.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    val LIST_ITEM_IN_TEST = 4

    @Before
    fun init() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun testActivity_inView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    // Visibility
    @Test
    fun testVisibility_title() {
        onView(withId(R.id.tv_screen_title))
                .check(matches(isDisplayed())) // method 1

        onView(withId(R.id.tv_screen_title))
                .check(matches(withText(R.string.weather))) // method 1
    }

    // Text
    @Test
    fun testVisibility_DaysForecastRecyclerView() {
        onView(withId(R.id.rc_days))
                .check(matches(isDisplayed()))
    }


    @Test
    fun testScroll_DaysForecastRecyclerView() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.rc_days))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ForecastAdapter.ViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

}
















