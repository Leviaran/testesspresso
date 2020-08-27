package com.esspresso.testespresso

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.internal.matchers.TypeSafeMatcher
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeUiTest {

    @Rule
    @JvmField
    val activityScenarioRules = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun changeTestActivity() {
//        R.id.et_username
//        R.id.et_pass
//        R.id.btn_action
//        R.id.et_status

    }

    fun withFontSize(expectedSize: Int): Matcher<View> {
        return object :
            BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun matchesSafely(target: TextView): Boolean {
                return true
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("with fontSize: ")
                description?.appendValue(expectedSize)
            }
        }
    }


    private fun setTextViewVisibitity(value: Boolean): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun perform(uiController: UiController?, view: View) {
                view.visibility = if (value) View.VISIBLE else View.GONE
            }

            override fun getDescription(): String {
                return "Show / Hide View"
            }
        }
    }

    fun isTextInLines(lines: Int): TypeSafeMatcher<View?> {
        return object : TypeSafeMatcher<View?>() {
            override fun matchesSafely(item: View?): Boolean {
                return (item as TextView).lineCount == lines
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("isTextInLines")
            }

        }
    }
}
