package akokuliuk.todoapp.presentation.add_task_internal

import akokuliuk.todoapp.R
import akokuliuk.todoapp.data.remote.RemoteTaskSource
import akokuliuk.todoapp.di.applicationComponent
import akokuliuk.todoapp.domain.models.Task
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Helper screen that allows to add a new tasks to API
 * FOR DEBUG PURPOSE ONLY, must be replaced with proper Add Task screen
 */
class AddTaskInternalFragment : Fragment() {

    @Inject
    lateinit var remoteTaskSource: RemoteTaskSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment__add_task_internal, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.create_task).setOnClickListener {
            val task = Task(
                name = view.findViewById<EditText>(R.id.task_name).text.toString(),
                note = view.findViewById<EditText>(R.id.task_description).text.toString(),
                isDone = false
            )
            GlobalScope.launch {
                applicationComponent().provideRetomeTaskSource()
                    .createTask(task)
            }
        }
    }


}