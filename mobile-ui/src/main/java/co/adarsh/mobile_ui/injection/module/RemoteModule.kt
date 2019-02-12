package co.adarsh.mobile_ui.injection.module

import co.adarsh.data.repository.ProjectsRemote
import co.adarsh.mobile_ui.BuildConfig
import co.adarsh.remote.ProjectsRemoteImpl
import co.adarsh.remote.service.GithubTrendingService
import co.adarsh.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Dagger Module to provide {@link ProjectsRemote}  Object for dependency injection
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}