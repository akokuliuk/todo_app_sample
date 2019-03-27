package akokuliuk.todoapp

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables


fun isNotDisplayed(): ViewAssertion {
    return ViewAssertion { view, _ ->
        if (view != null
            && ViewMatchers.isDisplayed().matches(view)
            && ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE).matches(view)
        ) {
            throw AssertionError("View is present in the hierarchy and Displayed: " + HumanReadables.describe(view))
        }
    }
}