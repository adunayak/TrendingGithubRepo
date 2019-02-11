package co.adarsh.remote.test.factory

import co.adarsh.remote.model.ProjectModel

object ProjectFactory {

    fun makeProjectModel(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomInt(), DataFactory.randomUuid(),
                OwnerFactory.makeOwnerModel())
    }

}