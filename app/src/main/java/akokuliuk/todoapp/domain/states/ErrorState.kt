package akokuliuk.todoapp.domain.states

import akokuliuk.todoapp.domain.events.StateEvent


interface ErrorState {
    val errorEvent: StateEvent<Error>?
    fun applyErrorEvent(error: Error)
}
