package akokuliuk.todoapp.unittest.presentation.my_list

import akokuliuk.todoapp.presentation.my_list.MyListViewModel
import com.groupon.grox.rxjava2.RxStores
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class MyListViewModelTest {


    @Test
    fun shows_no_task_label_if_no_task_loaded() {
        val vm = MyListViewModel(mock {
            onBlocking { getTasks() }.doReturn(emptyList())
        })

        vm.onResumeEvent()
        RxStores.states(vm.store)
            .test()
            .assertValue {
                it.showNoTasksLabel
            }
    }


    //TODO: Expose to the helper class
    companion object {

        @BeforeClass
        @JvmStatic
        fun setupSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

            Dispatchers.setMain(Dispatchers.Unconfined)
        }
    }
}