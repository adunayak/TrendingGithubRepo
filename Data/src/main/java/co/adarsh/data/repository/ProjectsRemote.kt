package co.adarsh.data.repository

import co.adarsh.data.model.ProjectEntity
import io.reactivex.Flowable

/**
 *  Interface implemented by remote layer to provides remote result
 */
interface ProjectsRemote {

    fun getProjects(): Flowable<List<ProjectEntity>>

}