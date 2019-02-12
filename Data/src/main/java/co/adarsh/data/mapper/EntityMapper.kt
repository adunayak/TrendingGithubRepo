package co.adarsh.data.mapper

/**
 * interface to map the pojo models from Domain to Data and vice versa
 */
interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E

}