package akokuliuk.todoapp.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.groupon.grox.Store
import com.groupon.grox.rxjava2.RxStores
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class FluxViewModel<T> : BaseViewModel() {

    internal var isInStubMode: Boolean = false

    val store by lazy { Store(provideInitialState()) }
    val stateObservable: Flowable<T> get() = RxStores.states(store).toFlowable(BackpressureStrategy.LATEST)
    val lifecycleDisposable = CompositeDisposable()


    fun bindState(block: (Flowable<T>) -> Disposable) {
        if (!isInStubMode) {
            lifecycleDisposable.add(block(stateObservable))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clearLifecycleDisposable() {
        lifecycleDisposable.clear()
    }

    abstract fun provideInitialState(): T
}
