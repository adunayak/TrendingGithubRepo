package co.adarsh.domain.interactor.bookmark

import co.adarsh.domain.ObservableUseCase
import co.adarsh.domain.executor.PostExecutionThread
import co.adarsh.domain.model.Project
import co.adarsh.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  Use case {@link ObservableUseCase} to get book marked a project.
 *
 *  @param ProjectsRepository
 *  @param PostExecutionThread
 */
open class GetBookmarkedProjects @Inject constructor(
        private val projectsRepository: @JvmSuppressWildcards ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    /**
     * Use case {@link ObservableUseCase} to provide project
     *
     * @param Nothing
     */
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }

}