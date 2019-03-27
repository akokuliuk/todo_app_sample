package akokuliuk.todoapp.domain.models


//TODO: Task should be mutable?
data class Task(
    val id: String? = null,
    val name: String,
    val note: String?,
    val isDone: Boolean
)
