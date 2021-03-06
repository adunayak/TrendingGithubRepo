package co.adarsh.domain.interactor.bookmark

import co.adarsh.domain.executor.PostExecutionThread
import co.adarsh.domain.CompletableUseCase
import co.adarsh.domain.interactor.bookmark.BookmarkProject.Params
import co.adarsh.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 *  Use case [CompletableUseCase] to book mark a project.
 *
 *  @param ProjectsRepository
 *  @param PostExecutionThread
 */
open class BookmarkProject @Inject constructor(
        private val projectsRepository: @JvmSuppressWildcards ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    /**
     * Use case [CompletableUseCase] to provide project
     *
     * @param Params project ID is taken as input
     */
    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return projectsRepository.bookmarkProject(params.projectId)
    }

    /**
     *  data class to construct use case parameter
     *
     *  @param String : Project ID to book mark
     */
    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}