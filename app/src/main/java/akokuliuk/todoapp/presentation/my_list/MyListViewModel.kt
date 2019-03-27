package akokuliuk.todoapp.presentation.my_list

import akokuliuk.todoapp.data.remote.AuthenticationSource
import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.presentation.base.FluxViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.launch
import javax.inject.Inject


class MyListViewModel @Inject constructor(
    private val authenticationSource: AuthenticationSource
) : FluxViewModel<MyListState>() {

    override fun provideInitialState() = MyListMutableState(false, tasks = listOf(
        Task("1", "Task 1", "Desc 1", true),
        Task("2", "Task 1", "Desc 1", true),
        Task("3", "Task 1", "Desc 1", true),
        Task("4", "Task 1", "Desc 1", true),
        Task("5", "Task 1", "Desc 1", true),
        Task("6", "Task 1", "Desc 1", true)
    )) as MyListState


    fun setTaskDone(task: Task, isTaskDone: Boolean){

    }

    fun onTaskClick(task: Task){
        store.dispatch {
            it.mutate<MyListMutableState>().apply {
                tasks = tasks.orEmpty().plus(Task(System.nanoTime().toString(), "Completed task", "Desc", true))
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun test(){
        launch {
            authenticationSource.authenticate()
        }
    }
}