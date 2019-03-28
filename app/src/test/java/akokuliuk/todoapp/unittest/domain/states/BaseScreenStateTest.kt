package akokuliuk.todoapp.unittest.domain.states

import akokuliuk.todoapp.presentation.my_list.MyListMutableState
import org.junit.Test


class BaseScreenStateTest {

    @Test
    fun mutateCreatesNewInstance() {
        MyListMutableState().checkMutation()
    }

}