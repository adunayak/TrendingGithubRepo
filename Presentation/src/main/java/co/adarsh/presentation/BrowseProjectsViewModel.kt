package co.adarsh.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.adarsh.domain.interactor.bookmark.BookmarkProject
import co.adarsh.domain.interactor.bookmark.UnbookmarkProject
import co.adarsh.domain.interactor.browse.GetProjects
import co.adarsh.domain.model.Project
import co.adarsh.presentation.mapper.ProjectViewMapper
import co.adarsh.presentation.model.ProjectView
import co.adarsh.presentation.state.Resource
import co.adarsh.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * View Model to fetch projects using use case classes and present to UI layer using live data
 *
 * @param GetProjects Use case to get all the projects
 * @param BookmarkProject Use case to book mark a project
 * @param UnbookmarkProject Use case to un book mark a project
 * @param ProjectViewMapper Mapper to map the presentation model to UI model
 *
 */
open class BrowseProjectsViewModel @Inject internal constructor(
        private val getProjects: GetProjects?,
        private val bookmarkProject: BookmarkProject,
        private val unBookmarkProject: UnbookmarkProject,
        private val mapper: ProjectViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    /**
     *  When view no longer exists unsubscribe all the observers,
     *  so that nothing notified after async operation completes
     */
    override fun onCleared() {
        getProjects?.dispose()
        super.onCleared()
    }

    /**
     *  List<ProjectView> [LiveData] to handle config changes.
     */
    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    /**
     * Load projects asynchronously and post loading message to UI
     */
    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getProjects?.execute(ProjectsSubscriber())
    }

    /**
     * Book mark a project asynchronously. No result is triggered as use case is of type completable type
     */
    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
                BookmarkProject.Params.forProject(projectId))
    }

    /**
     * Un book mark a project asynchronously. No result is triggered as use case is of type completable type
     */
    fun unbookmarkProject(projectId: String) {
        return unBookmarkProject.execute(BookmarkProjectsSubscriber(),
                UnbookmarkProject.Params.forProject(projectId))
    }

    /**
     * [DisposableObserver] passed to use case to observe project data fetch.
     *  Live data posts success message if fetching is successful else error message is triggered
     */
    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    /**
     * [DisposableCompletableObserver] passed to use case to observe project book mark and unbook mark.
     *  Live data posts success message if operation is successful else error message is triggered
     */
    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }

    }
}