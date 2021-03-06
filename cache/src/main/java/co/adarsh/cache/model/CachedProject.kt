package co.adarsh.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import co.adarsh.cache.db.ProjectConstants

/**
 *  Model to create Project table and properties using room lib annotation
 *
 */
@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject(
        @PrimaryKey
        @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID)
        var id: String,
        var name: String,
        var fullName: String,
        var starCount: String,
        var dateCreated: String,
        var ownerName: String,
        var ownerAvatar: String,
        @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED)
        var isBookmarked: Boolean
)