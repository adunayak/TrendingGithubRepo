package co.adarsh.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import co.adarsh.cache.db.ConfigConstants

/**
 *  Model to create config table and properties using room lib annotation
 *
 */
@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        var lastCacheTime: Long)