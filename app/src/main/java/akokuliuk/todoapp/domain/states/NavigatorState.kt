package akokuliuk.todoapp.domain.states

import akokuliuk.todoapp.domain.events.RedirectEvent
import akokuliuk.todoapp.domain.events.StateEvent


interface NavigatorState {
    val redirectEvent: StateEvent<RedirectEvent>?
    fun applyRedirectEvent(event: RedirectEvent)
}