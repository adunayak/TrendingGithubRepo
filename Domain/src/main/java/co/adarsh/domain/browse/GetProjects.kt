package co.adarsh.domain.interactor.browse

import co.adarsh.domain.ObservableUseCase
import co.adarsh.domain.executor.PostExecutionThread
import co.adarsh.domain.model.Project
import co.adarsh.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
        private val projectsRepository: @JvmSuppressWildcards ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}