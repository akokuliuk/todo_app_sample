package akokuliuk.todoapp.presentation.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class FluxFragment<VIEW_MODEL : ViewModel, COMPONENT : Any> : Fragment() {

    lateinit var viewModel: VIEW_MODEL
        internal set

    lateinit var component: COMPONENT
        internal set

    abstract fun provideViewModel(): VIEW_MODEL
    abstract fun createComponent(): COMPONENT

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        component = createComponent()
        viewModel = provideViewModel()
    }
}