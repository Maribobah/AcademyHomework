package ru.maribobah.academyhomework.data.localdb

import android.provider.BaseColumns

object DbContract {

    const val DATABASE_NAME = "app_database"

    object Movies {
        const val TABLE_NAME = "movies"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_RATE = "rate"
        const val COLUMN_NAME_LIKE = "like"
        const val COLUMN_NAME_STARS = "stars"
        const val COLUMN_NAME_REVIEWS = "reviews"
        const val COLUMN_NAME_DURATION = "duration"
        const val COLUMN_NAME_POSTER = "poster"
        const val COLUMN_NAME_BACKDROP = "backdrop"
        const val COLUMN_NAME_STORYLINE = "storyline"
        const val COLUMN_NAME_GENRES = "genres"

    }

    object Actors {
        const val TABLE_NAME = "actors"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_AVATAR = "avatar"
    }

    object MoviesByCategory {
        const val TABLE_NAME = "movies_by_category"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_CATEGORY = "category"
        const val COLUMN_NAME_MOVIE = "movie"
    }

    object ActorsByMovie {
        const val TABLE_NAME = "actors_by_movie"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ACTOR = "actor"
        const val COLUMN_NAME_MOVIE = "movie"
    }

}