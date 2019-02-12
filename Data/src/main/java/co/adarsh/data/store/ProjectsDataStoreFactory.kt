package co.adarsh.data.store

import co.adarsh.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 *  Factory class to provide remote or cache data store.
 *
 *  @param ProjectsCacheDataStore
 *  @param ProjectsRemoteDataStore
 */
open class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    /**
     * @return ProjectsDataStore : Cached data provider is provided in case projects are cached
     * and not expired else remote data store is returned
     */
    open fun getDataStore(projectsCached: Boolean,
                          cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    /**
     * @return ProjectsDataStore
     */
    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

    /**
     * @return ProjectsDataStore
     */
    fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStore
    }
}