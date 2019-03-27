package akokuliuk.todoapp.utils.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Well, basically it's dirty hack, ViewModel factory shouldn't be a singleton
 */
object ViewModelFactory {


    fun <T : ViewModel> provideViewModel(
        activity: FragmentActivity,
        viewModelClass: Class<T>,
        block: () -> T
    ): T {
        return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass == viewModelClass) {
                    @Suppress("UNCHECKED_CAST")
                    return block() as T
                } else {
                    throw RuntimeException("Can't provide model {${modelClass.name}")
                }
            }
        }).get(viewModelClass).apply {
            if (this is LifecycleObserver) {
                activity.lifecycle.addObserver(this)
            }
        }
    }


    fun <T : ViewModel> provideViewModel(
        fragment: Fragment,
        viewModelClass: Class<T>,
        block: () -> T
    ): T {
        return ViewModelProviders.of(fragment, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass == viewModelClass) {
                    @Suppress("UNCHECKED_CAST")
                    return block() as T
                } else {
                    throw RuntimeException("Can't provide model {${modelClass.name}")
                }
            }
        }).get(viewModelClass).apply {
            if (this is LifecycleObserver) {
                fragment.lifecycle.addObserver(this)
            }
        }
    }

    fun detachFromLifecycle(viewModel: ViewModel, fragment: Fragment) {
        if (viewModel is LifecycleObserver) {
            fragment.lifecycle.removeObserver(viewModel)
        }
    }
}
