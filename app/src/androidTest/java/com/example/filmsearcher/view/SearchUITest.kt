package com.example.filmsearcher.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmsearcher.R

import org.junit.Rule
import org.junit.Test

class SearchUITest {
    private val title = "all the bright places";

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickSearchButton_opensSearchFragment () {
        onView(withId(R.id.search_field)).perform(ViewActions.typeText(title))

        onView(withId(R.id.search_button)).perform(ViewActions.click())

        Thread.sleep(1_000)
        Espresso.onView(withId(R.id.movie_title))
            .check(matches(withText(title)))
    }
    @Test
    fun clickSearchButton_searchQueryRemains() {
        onView(withId(R.id.search_field)).perform(ViewActions.typeText(title))
        onView(withId(R.id.search_button)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.search_field))
            .check(matches(withText(title)))
    }


}