package akokuliuk.todoapp.presentation.my_list

import akokuliuk.todoapp.R
import akokuliuk.todoapp.di.applicationComponent
import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.presentation.add_task_internal.AddTaskInternalFragment
import akokuliuk.todoapp.presentation.base.FluxFragment
import akokuliuk.todoapp.presentation.my_list.items.TaskGroupHeader_
import akokuliuk.todoapp.presentation.my_list.items.TaskItem_
import akokuliuk.todoapp.utils.adapter.SimpleController
import akokuliuk.todoapp.utils.android.ViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import dagger.Module
import io.reactivex.android.schedulers.AndroidSchedulers


@Module
class MyListFragment : FluxFragment<MyListViewModel, MyListComponent>() {

    //TODO: In general we should use butterknife or databinding to bind view instead of manual binding
    private lateinit var taskList: EpoxyRecyclerView
    private lateinit var noTasksLabel: View
    private lateinit var controller: SimpleController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment__my_list, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskList = view.findViewById(R.id.task_list)
        noTasksLabel = view.findViewById(R.id.no_tasks_label_layout)
        if (!this::controller.isInitialized)
            controller = SimpleController()
        taskList.setController(controller)

        //TODO: For debug purpose only
        view.findViewById<View>(R.id.add_task).setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, AddTaskInternalFragment())
                .addToBackStack("add_task")
                .commit()
        }
    }

    /**
     * Update UI state here
     */
    internal fun dispatchState(state: MyListState) {
        noTasksLabel.visibility = if (state.showNoTasksLabel) View.VISIBLE else View.GONE
        taskList.visibility = if (state.tasks?.isNotEmpty() == true) View.VISIBLE else View.GONE

        controller.buildWithModels {
            state.tasks?.filter { !it.isDone }.takeIf { !it.isNullOrEmpty() }?.let {
                attachTaskGroup("active_tasks", resources.getString(R.string.screen__my_list__active_tasks_header))
                it.forEach { task -> attachTask(task) }
            }

            state.tasks?.filter { it.isDone }.takeIf { !it.isNullOrEmpty() }?.let {
                attachTaskGroup(
                    "completed_tasks",
                    resources.getString(R.string.screen__my_list__completed_tasks_header)
                )
                it.forEach { task -> attachTask(task) }
            }
        }
    }

    private fun EpoxyController.attachTask(task: Task) {
        TaskItem_()
            .id(task.id)
            .name(task.name)
            .description(task.note)
            .done(task.isDone)
            .onClick { _ ->
                viewModel.onTaskClick(task)
            }
            .onCheckedChanged {
                viewModel.setTaskDone(task, it)
            }
            .addTo(this)
    }

    private fun EpoxyController.attachTaskGroup(groupId: String, groupName: String) {
        TaskGroupHeader_()
            .id(groupId)
            .taskGroupName(groupName)
            .addTo(this)
    }

    /**
     * Subscribe to the state updates
     */
    override fun onResume() {
        super.onResume()
        viewModel.bindState {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dispatchState)
        }
    }

    override fun provideViewModel() =
        ViewModelFactory.provideViewModel(this, MyListViewModel::class.java) { component.provideViewModel() }

    override fun createComponent() = applicationComponent().provideMyListComponent(this)
}
