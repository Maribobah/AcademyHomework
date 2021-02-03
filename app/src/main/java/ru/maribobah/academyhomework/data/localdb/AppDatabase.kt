package ru.maribobah.academyhomework.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.maribobah.academyhomework.data.localdb.entity.*

@Database(
    entities = [
        ActorEntity::class,
        MovieEntity::class,
        ActorsByMovie::class,
        MoviesByCategory::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = buildDatabase(context)
                INSTANCE = instance
                return instance
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DbContract.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}