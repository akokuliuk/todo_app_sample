package akokuliuk.todoapp.unittest.domain.states

import akokuliuk.todoapp.presentation.activity.MainActivityMutableState
import akokuliuk.todoapp.presentation.base.BaseScreenMutableState
import akokuliuk.todoapp.presentation.base.BaseScreenState
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class BaseScreenStateTest{

    @Test
    fun mutateCreatesNewInstance(){
        MainActivityMutableState("Hello").checkMutation()
    }

}