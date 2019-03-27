package akokuliuk.todoapp.presentation.activity

import akokuliuk.todoapp.presentation.base.FluxViewModel


class MainActivityViewModel : FluxViewModel<MainActivityState>() {
    override fun provideInitialState() = MainActivityMutableState("Hello")

    fun addOne() = store.dispatch{
        it.mutate<MainActivityMutableState>().apply {
            title += "1"
        }
    }
}
