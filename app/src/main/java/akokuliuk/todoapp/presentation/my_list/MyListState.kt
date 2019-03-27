package akokuliuk.todoapp.presentation.my_list

import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.presentation.base.BaseScreenMutableState
import akokuliuk.todoapp.presentation.base.BaseScreenState


interface MyListState : BaseScreenState {
    val showNoTasksLabel: Boolean
    val tasks: List<Task>?
}


data class MyListMutableState(
    override var showNoTasksLabel: Boolean,
    override var tasks: List<Task>?
) : BaseScreenMutableState(), MyListState {

    @Suppress("UNCHECKED_CAST")
    override fun <T : BaseScreenMutableState> mutate(): T = copy() as T
}