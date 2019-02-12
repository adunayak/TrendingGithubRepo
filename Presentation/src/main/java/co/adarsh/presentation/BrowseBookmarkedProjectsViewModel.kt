package co.adarsh.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.adarsh.domain.interactor.bookmark.GetBookmarkedProjects
import co.adarsh.domain.model.Project
import co.adarsh.presentation.mapper.ProjectViewMapper
import co.adarsh.presentation.model.ProjectView
import co.adarsh.presentation.state.Resource
import co.adarsh.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * View Model to fetch book marked projects using use case classes and present to UI layer using live data
 *
 * @param GetBookmarkedProjects Use case to get all the bookmarked projects
 * @param ProjectViewMapper Mapper to map the presentation model to UI model
 *
 */
class BrowseBookmarkedProjectsViewModel @Inject constructor(
        private val getBookmarkedProjects: GetBookmarkedProjects,
        private val mapper: ProjectViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> =
            MutableLiveData()

    /**
     *  When view no longer exists unsubscribe all the observers,
     *  so that nothing notified after async operation completes
     */
    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    /**
     *  List<ProjectView> [LiveData] to handle config changes.
     */
    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    /**
     * Load bookmarked projects asynchronously and post loading message to UI
     */
    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedProjects.execute(ProjectsSubscriber())
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

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                    e.localizedMessage))
        }

        override fun onComplete() { }

    }

}