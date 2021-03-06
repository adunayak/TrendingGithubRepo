package co.adarsh.mobile_ui.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.adarsh.mobile_ui.injection.ViewModelFactory
import co.adarsh.presentation.BrowseBookmarkedProjectsViewModel
import co.adarsh.presentation.BrowseProjectsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Dagger Module to provide View Model Object for dependency injection
 */
@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
            viewModel: BrowseBookmarkedProjectsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)