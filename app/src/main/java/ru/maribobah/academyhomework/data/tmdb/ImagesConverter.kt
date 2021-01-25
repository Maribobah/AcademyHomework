package ru.maribobah.academyhomework.data.tmdb

import ru.maribobah.academyhomework.data.models.ConfigurationImage

class ImagesConverter {

    private var imagesInfo: ConfigurationImage? = null

    suspend fun getFullBackdropPath(path: String?): String {
        val conf = getImagesInfo()
        val size = getAppropriateSizeFromList(conf.backdropSizes, BACKDROP_MAX_SIZE)
        return "${conf.secureBaseUrl}/$size/${path}"
    }

    suspend fun getFullPosterPath(path: String?): String {
        val conf = getImagesInfo()
        val size = getAppropriateSizeFromList(conf.posterSizes, POSTER_MAX_SIZE)
        return "${conf.secureBaseUrl}/$size/${path}"
    }

    suspend fun getFullProfilePath(path: String?): String {
        val conf = getImagesInfo()
        val size = getAppropriateSizeFromList(conf.profileSizes, PROFILE_MAX_SIZE)
        return "${conf.secureBaseUrl}/$size/${path}"
    }

    private suspend fun getImagesInfo(): ConfigurationImage {
        val tempImagesInfo = imagesInfo
        if (tempImagesInfo != null) {
            return tempImagesInfo
        }
        val tmdbApi = TmdbApi.getInstance()
        return tmdbApi.services.configuration().images
    }

    private fun getAppropriateSizeFromList(list: List<String>, size: Int): String? {
        return list.minByOrNull { width ->
            when {
                width == ORIGINAL_SIZE -> Int.MAX_VALUE - 1
                getIntSizeFromStringWidth(width) < size -> Int.MAX_VALUE
                else -> getIntSizeFromStringWidth(width)
            }
        }
    }

    private fun getIntSizeFromStringWidth(width: String): Int = width.drop(QTY_NON_NUMERIC_SYMBOLS).toInt()

    private companion object {
        private const val BACKDROP_MAX_SIZE = 375
        private const val POSTER_MAX_SIZE = 166
        private const val PROFILE_MAX_SIZE = 80
        private const val ORIGINAL_SIZE = "original"
        private const val QTY_NON_NUMERIC_SYMBOLS = 1
    }
}