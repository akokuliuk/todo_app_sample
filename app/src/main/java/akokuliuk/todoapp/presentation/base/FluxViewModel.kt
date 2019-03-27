package akokuliuk.todoapp.presentation.base

import akokuliuk.todoapp.utils.rx.AppSchedulers
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.groupon.grox.Store
import com.groupon.grox.rxjava2.RxStores
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class FluxViewModel<T> : BaseViewModel() {

    val store by lazy { Store(provideInitialState()) }
    val stateObservable: Flowable<T> get() = RxStores.states(store).toFlowable(BackpressureStrategy.LATEST)

    val lifecycleDisposable = CompositeDisposable()

    inline fun bindState(block: (Flowable<T>) -> Disposable) {
        lifecycleDisposable.add(block(stateObservable))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clearLifecycleDisposable() {
        lifecycleDisposable.clear()
    }

    abstract fun provideInitialState(): T
}
