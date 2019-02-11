package co.adarsh.mobile_ui.injection.module

import co.adarsh.domain.executor.PostExecutionThread
import co.adarsh.mobile_ui.UiThread
import co.adarsh.mobile_ui.bookmarked.BookmarkedActivity
import co.adarsh.mobile_ui.browse.BrowseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}