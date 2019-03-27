package akokuliuk.todoapp.presentation.activity

import akokuliuk.todoapp.presentation.base.BaseScreenMutableState
import akokuliuk.todoapp.presentation.base.BaseScreenState


interface MainActivityState : BaseScreenState {
    val title: String
}


data class MainActivityMutableState(
    override var title: String
) : BaseScreenMutableState(), MainActivityState {
    @Suppress("UNCHECKED_CAST")
    override fun <T : BaseScreenMutableState> mutate(): T = copy() as T
}
