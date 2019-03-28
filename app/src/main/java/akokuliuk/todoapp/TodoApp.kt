package akokuliuk.todoapp

import akokuliuk.todoapp.di.ApplicationComponent
import akokuliuk.todoapp.di.DaggerApplicationComponent
import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class TodoApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.builder().todoApp(this).build()
    }

    @Provides
    fun provideTodoApp(): TodoApp = this

    /**
     * Basically Application is always singleton, we just expose it to the app
     */
    companion object {
        lateinit var instance: TodoApp
            private set
    }
}
