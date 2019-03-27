package akokuliuk.todoapp.domain.events


data class RedirectEvent(
    val screenUri: String,
    val addToBAckStack: Boolean
)