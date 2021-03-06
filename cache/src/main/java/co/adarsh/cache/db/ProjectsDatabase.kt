package co.adarsh.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import co.adarsh.cache.dao.CachedProjectsDao
import co.adarsh.cache.dao.ConfigDao
import co.adarsh.cache.model.CachedProject
import co.adarsh.cache.model.Config
import javax.inject.Inject

/**
 *  To create Project database using room library. Creates sqlite database using room database builder
 *
 */
@Database(entities = arrayOf(CachedProject::class,
        Config::class), version = 1)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: ProjectsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ProjectsDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                ProjectsDatabase::class.java, "projects.db")
                                .build()
                    }
                    return INSTANCE as ProjectsDatabase
                }
            }
            return INSTANCE as ProjectsDatabase
        }
    }

}