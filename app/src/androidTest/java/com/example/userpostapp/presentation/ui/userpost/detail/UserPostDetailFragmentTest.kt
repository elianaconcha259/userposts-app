package com.example.userpostapp.presentation.ui.userpost.detail


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.userpostapp.R
import com.example.userpostapp.presentation.ui.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserPostDetailFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.rvUserList),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
            allOf(
                withId(R.id.tvName), withText("Leanne Graham"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Leanne Graham")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
