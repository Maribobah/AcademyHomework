package ru.maribobah.academyhomework.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.maribobah.academyhomework.data.localdb.AppDatabase
import ru.maribobah.academyhomework.data.localdb.Dao
import ru.maribobah.academyhomework.data.localdb.DataMapper
import ru.maribobah.academyhomework.data.tmdb.ImagesConverter
import ru.maribobah.academyhomework.data.tmdb.TmdbApi
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideTmdbService() : TmdbApi.ServicesApi {
        return TmdbApi().services()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) : AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db : AppDatabase) : Dao {
        return db.dao()
    }

    @Singleton
    @Provides
    fun provideDataMapper(imagesConverter: ImagesConverter) : DataMapper {
        return DataMapper(imagesConverter)
    }

    @Singleton
    @Provides
    fun provideImagesConverter(tmdbService : TmdbApi.ServicesApi) : ImagesConverter {
        return ImagesConverter(tmdbService)
    }
}