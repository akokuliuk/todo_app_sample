package akokuliuk.todoapp.domain.events


class StateEvent<T>(val value: T) {
    var isHandled: Boolean = false
        private set

    fun handle() {
        isHandled = true
    }
}


inline fun <T> StateEvent<T>?.handle(block: (T) -> Boolean) {
    if (this != null && !isHandled) {
        if (block(value)) {
            handle()
        }
    }
}
