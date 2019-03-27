package akokuliuk.todoapp.screens

import akokuliuk.todoapp.R
import akokuliuk.todoapp.presentation.activity.MainActivity
import akokuliuk.todoapp.presentation.my_list.MyListFragment
import akokuliuk.todoapp.presentation.my_list.MyListMutableState
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class MyListFragmentTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun handleNoLabelState() {
        //TODO: Move fragment initialisation to outside of test
        val fragment = MyListFragment()
        UiThreadStatement.runOnUiThread {
            activityRule.activity.supportFragmentManager
                .beginTransaction()
                .replace(MainActivity.FragmentFrameId, fragment)
                .commitNow()

        }

        //TODO: Move state changing to some helper
        UiThreadStatement.runOnUiThread {
            fragment.dispatchState(MyListMutableState(showNoTasksLabel = true, tasks = null))
        }
        onView(withId(R.id.no_tasks_label)).check(matches(isDisplayed()))

        UiThreadStatement.runOnUiThread {
            fragment.dispatchState(MyListMutableState(showNoTasksLabel = false, tasks = null))
        }
        onView(withId(R.id.no_tasks_label)).check(matches(not(isDisplayed())))


    }
}