package ru.maribobah.academyhomework.data.localdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maribobah.academyhomework.data.localdb.DbContract

@Entity(
    tableName = DbContract.Actors.TABLE_NAME
)
data class ActorEntity(

    @PrimaryKey
    @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_NAME)
    val name: String,

    @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_AVATAR)
    val avatar: String
)