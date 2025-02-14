package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setUp() {
        Intents.init()
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun whenAddCourseActivityDisplayed() {
        onView(withId(R.id.action_add))
            .check(matches(isDisplayed()))
            .perform(click())
        Intents.intended(hasComponent(AddCourseActivity::class.java.name))
    }
}