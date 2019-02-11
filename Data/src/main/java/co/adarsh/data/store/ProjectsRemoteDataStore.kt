package co.adarsh.data.store

import co.adarsh.data.model.ProjectEntity
import co.adarsh.data.repository.ProjectsDataStore
import co.adarsh.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 *  Interface adapter layer {@link ProjectsDataStore} to provide the data from remote layer
 *
 *  @param ProjectsCache
 */
open class ProjectsRemoteDataStore @Inject constructor(
        private val projectsRemote: ProjectsRemote)
    : ProjectsDataStore {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

}