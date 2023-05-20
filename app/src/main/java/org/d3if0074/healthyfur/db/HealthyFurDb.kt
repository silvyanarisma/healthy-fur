package org.d3if0074.healthyfur.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoriEntity::class], version = 1, exportSchema = false)
abstract class HealthyFurDb : RoomDatabase() {

    abstract val dao: HistoriDao

    companion object {
        @Volatile
        private var INSTANCE: HealthyFurDb? = null
        fun getInstance(context: Context): HealthyFurDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HealthyFurDb::class.java,
                        "healthy_fur.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}