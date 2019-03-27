package akokuliuk.todoapp.presentation.my_list

import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.presentation.base.FluxViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject


class MyListViewModel @Inject constructor() : FluxViewModel<MyListState>() {
    override fun provideInitialState() = MyListMutableState(false, null) as MyListState


    fun setTaskDone(task: Task, isTaskDone: Boolean){

    }

    fun onTaskClick(task: Task){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        store.dispatch {
            it.mutate<MyListMutableState>().apply {
                showNoTasksLabel = false
                tasks = listOf(
                    Task("1", "Task 1", "Desc 1", false),
                    Task("2", "Task 1", "Desc 1", false),
                    Task("3", "Task 1", "Desc 1", false),
                    Task("4", "Task 1", "Desc 1", false),
                    Task("5", "Task 1", "Desc 1", false),
                    Task("6", "Task 1", "Desc 1", false)
                )
            }
        }
    }
}