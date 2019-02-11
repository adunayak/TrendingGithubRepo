package co.adarsh.cache.db

/**
 *  Singleton object to to maintain config table constants
 *
 */
object ConfigConstants {

    const val TABLE_NAME = "config"

    const val QUERY_CONFIG = "SELECT * FROM $TABLE_NAME"

}