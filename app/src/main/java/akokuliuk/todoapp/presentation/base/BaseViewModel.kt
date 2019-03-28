package akokuliuk.todoapp.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel(), CoroutineScope, LifecycleObserver {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = Job()

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
