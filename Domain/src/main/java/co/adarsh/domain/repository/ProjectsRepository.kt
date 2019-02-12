package co.adarsh.domain.repository

import co.adarsh.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * An interface which is implemented by data layer to provide the projects,
 * bookmark a project, un bookmark project and get book marked projects
 */
interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>

}