package akokuliuk.todoapp.screens

import akokuliuk.todoapp.R
import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.isNotDisplayed
import akokuliuk.todoapp.presentation.activity.MainActivity
import akokuliuk.todoapp.presentation.my_list.MyListFragment
import akokuliuk.todoapp.presentation.my_list.MyListMutableState
import akokuliuk.todoapp.presentation.my_list.MyListState
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class MyListFragmentTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var fragment: MyListFragment

    @Before
    fun runMyListFragment() {
        fragment = MyListFragment().apply {
            isInStubMode = true
        }

        UiThreadStatement.runOnUiThread {
            activityRule.activity.supportFragmentManager
                .beginTransaction()
                .replace(MainActivity.FragmentFrameId, fragment)
                .commitNow()
        }
    }

    @Test
    fun handleNoLabelState() {
        updateState(MyListMutableState(true))
        onView(withId(R.id.no_tasks_label)).check(matches(isDisplayed()))
        updateState(MyListMutableState(false))
        onView(withId(R.id.no_tasks_label)).check(isNotDisplayed())
    }

    @Test
    fun showsActiveTasks() {
        updateState(
            MyListMutableState(
                tasks = listOf(
                    Task("1", "My Task 1", "Desc 1", false),
                    Task("2", "My Task 2", "Desc 2", false)
                )
            )
        )

        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__active_tasks_header)))
            .check(matches(isDisplayed()))
        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__completed_tasks_header)))
            .check(isNotDisplayed())

        onView(withText("My Task 1")).check(matches(isDisplayed()))
        onView(withText("My Task 2")).check(matches(isDisplayed()))
    }

    @Test
    fun showsCompletedTasks() {
        updateState(
            MyListMutableState(
                tasks = listOf(
                    Task("1", "My Task 1", "Desc 1", true),
                    Task("2", "My Task 2", "Desc 2", true)
                )
            )
        )

        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__completed_tasks_header)))
            .check(matches(isDisplayed()))
        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__active_tasks_header)))
            .check(isNotDisplayed())

        onView(withText("My Task 1")).check(matches(isDisplayed()))
        onView(withText("My Task 2")).check(matches(isDisplayed()))
    }

    @Test
    fun showsBothTypeOfTasks() {
        updateState(
            MyListMutableState(
                tasks = listOf(
                    Task("1", "My Task 1", "Desc 1", false),
                    Task("2", "My Task 2", "Desc 2", true)
                )
            )
        )

        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__completed_tasks_header)))
            .check(matches(isDisplayed()))
        onView(withText(activityRule.activity.resources.getString(R.string.screen__my_list__active_tasks_header)))
            .check(matches(isDisplayed()))

        onView(withText("My Task 1")).check(matches(isDisplayed()))
        onView(withText("My Task 2")).check(matches(isDisplayed()))
    }


    private fun updateState(newState: MyListState) {
        UiThreadStatement.runOnUiThread {
            fragment.dispatchState(newState)
        }

        //Workaround to avoid flaky test cause by animations
        Thread.sleep(1000)
    }
}
