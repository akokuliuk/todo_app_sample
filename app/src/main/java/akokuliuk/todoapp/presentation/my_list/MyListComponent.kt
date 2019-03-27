package akokuliuk.todoapp.presentation.my_list

import dagger.Subcomponent


@Subcomponent(modules = [MyListFragment::class])
interface MyListComponent {
    fun provideViewModel(): MyListViewModel
}
