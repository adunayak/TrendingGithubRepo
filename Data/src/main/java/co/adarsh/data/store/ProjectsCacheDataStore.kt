package co.adarsh.data.store

import co.adarsh.data.model.ProjectEntity
import co.adarsh.data.repository.ProjectsCache
import co.adarsh.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 *  Interface adapter layer [ProjectsDataStore] to provide the data from cache layer
 *
 *  @param ProjectsCache
 */
open class ProjectsCacheDataStore @Inject constructor(
        private val projectsCache: ProjectsCache)
    : ProjectsDataStore {


    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
                .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }

}