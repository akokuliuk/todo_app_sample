package akokuliuk.todoapp.unittest.domain.states

import akokuliuk.todoapp.presentation.base.BaseScreenMutableState
import akokuliuk.todoapp.presentation.base.BaseScreenState
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue


fun BaseScreenMutableState.checkMutation() {
    assertFalse("mutation must create new state instance", this === mutate<BaseScreenMutableState>())
    assertTrue("mutation must create equal object", this == mutate())
}