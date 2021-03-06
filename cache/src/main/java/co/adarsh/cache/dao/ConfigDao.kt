package co.adarsh.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.adarsh.cache.db.ConfigConstants
import co.adarsh.cache.model.Config
import io.reactivex.Flowable

/**
 *  Dao interface to query and cache and update expiry config
 *
 */
@Dao
abstract class ConfigDao {

    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)

}