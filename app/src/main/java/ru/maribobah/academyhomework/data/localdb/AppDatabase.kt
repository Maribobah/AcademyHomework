package ru.maribobah.academyhomework.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maribobah.academyhomework.data.localdb.entity.*

@Database(
    entities = [
        ActorEntity::class,
        MovieEntity::class,
        ActorsByMovieEntity::class,
        MoviesByCategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

}