package akokuliuk.todoapp.unittest.domain.events

import akokuliuk.todoapp.domain.events.StateEvent
import akokuliuk.todoapp.domain.events.handle
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*


class StateEventTest{

    @Test
    fun handle_method_can_be_executed_only_once(){
        val event = StateEvent("Hello")
        val handler = Mockito.spy({_: String -> true})
        event.handle(handler)
        event.handle(handler)
        verify(handler, times(1)).invoke(ArgumentMatchers.anyString())
    }
}