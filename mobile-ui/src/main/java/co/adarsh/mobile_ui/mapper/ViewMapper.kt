package co.adarsh.mobile_ui.mapper

/**
 *  Mapper interface to map data from presentation to View or vice versa
 */
interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}