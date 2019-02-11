package co.adarsh.remote.test.factory

import co.adarsh.remote.model.OwnerModel

object OwnerFactory {

    fun makeOwnerModel(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

}