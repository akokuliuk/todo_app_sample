package akokuliuk.todoapp.di

import akokuliuk.todoapp.TodoApp
import akokuliuk.todoapp.presentation.my_list.MyListComponent
import akokuliuk.todoapp.presentation.my_list.MyListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [TodoApp::class])
interface ApplicationComponent {
    fun provideMyListComponent(module: MyListFragment): MyListComponent
}


fun applicationComponent() = TodoApp.instance.applicationComponent