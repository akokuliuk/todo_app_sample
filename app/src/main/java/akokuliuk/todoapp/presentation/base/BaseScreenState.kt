package akokuliuk.todoapp.presentation.base

import akokuliuk.todoapp.domain.events.RedirectEvent
import akokuliuk.todoapp.domain.events.StateEvent
import akokuliuk.todoapp.domain.states.ErrorState
import akokuliuk.todoapp.domain.states.NavigatorState


interface BaseScreenState : ErrorState, NavigatorState {
    fun <T : BaseScreenMutableState> mutate(): T
}

abstract class BaseScreenMutableState : BaseScreenState {

    override var errorEvent: StateEvent<Error>? = null
    override var redirectEvent: StateEvent<RedirectEvent>? = null

    override fun applyErrorEvent(error: Error) {
        errorEvent = StateEvent(error)
    }

    override fun applyRedirectEvent(event: RedirectEvent) {
        redirectEvent = StateEvent(event)
    }
}