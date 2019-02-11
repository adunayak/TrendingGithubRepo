package co.adarsh.remote.mapper

import co.adarsh.data.model.ProjectEntity
import co.adarsh.remote.model.ProjectModel
import javax.inject.Inject

/**
 * Mapper to map the remote data model to data model and vice versa
 */
class ProjectsResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
                model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, false)
    }

}