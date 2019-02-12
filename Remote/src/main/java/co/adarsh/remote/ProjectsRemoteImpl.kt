package co.adarsh.remote

import co.adarsh.data.model.ProjectEntity
import co.adarsh.data.repository.ProjectsRemote
import co.adarsh.remote.mapper.ProjectsResponseModelMapper
import co.adarsh.remote.service.GithubTrendingService
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Implements [ProjectsRemote] to provide data from server and map it to project data model
 */
class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper)
    : ProjectsRemote {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map { mapper.mapFromModel(it) }
                }
    }
}