package co.adarsh.presentation.mapper

/**
 * Mapper interface to map presentation model to Domain model or vice versa
 */
interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}