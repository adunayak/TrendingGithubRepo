package co.adarsh.data

import co.adarsh.data.mapper.ProjectMapper
import co.adarsh.data.repository.ProjectsCache
import co.adarsh.data.store.ProjectsDataStoreFactory
import co.adarsh.domain.model.Project
import co.adarsh.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 *  Implements {@link ProjectsRepository} to provide the data to usecase
 *
 *  @param ProjectMapper
 *  @param ProjectsCache
 *  @param ProjectsDataStoreFactory
 */
class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory)
    : @JvmSuppressWildcards ProjectsRepository {

    /**
     * Method to return trending github projects.
     *
     * @return Observable<List<Project>>  : return cache projects if data is cached and not expired
     * else data will be fetched from server and cached.
     */
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),

                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects().toObservable()
                            .distinctUntilChanged()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    /**
     * Method to book mark a project in local database.
     *
     * @param String : project id to book mark.
     *
     * @return Completable  : Nothing doing
     */
    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    /**
     * Method to un book mark a project from local database.
     *
     * @param String : project id to book mark.
     *
     * @return Completable  : Nothing doing
     */
    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    /**
     * Method to return bookmarked projects.
     *
     * @return Observable<List<Project>>  : returns all the bookmarked projects from database
     */
    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().toObservable()
                .map { it.map { mapper.mapFromEntity(it) } }
    }

}