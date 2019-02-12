package co.adarsh.mobile_ui.injection.module

import android.app.Application
import co.adarsh.cache.ProjectsCacheImpl
import co.adarsh.cache.db.ProjectsDatabase
import co.adarsh.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Dagger Module to provide project cache for dependency injection
 */
@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}