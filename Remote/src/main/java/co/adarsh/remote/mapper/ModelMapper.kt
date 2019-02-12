package co.adarsh.remote.mapper

/**
 * Mapper Interface to map the remote data model to data model and vice versa
 */
interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

}