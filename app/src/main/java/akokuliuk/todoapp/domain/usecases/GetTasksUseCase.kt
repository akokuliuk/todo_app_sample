package akokuliuk.todoapp.domain.usecases

import akokuliuk.todoapp.data.remote.RemoteTaskSource
import akokuliuk.todoapp.domain.models.Task
import javax.inject.Inject

//TODO: Should be exposed with interface
class GetTasksUseCase @Inject constructor(
    private val taskSource: RemoteTaskSource
) {

    //TODO: Such operation should be done using repository
    suspend fun getTasks(): List<Task> =
        taskSource.loadTasks()

}