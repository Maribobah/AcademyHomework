package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ConfigurationImage(
    @SerialName("base_url")
    val baseUrl: String,
    @SerialName("secure_base_url")
    val secureBaseUrl: String,
    @SerialName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerialName("poster_sizes")
    val posterSizes: List<String>,
    @SerialName("profile_sizes")
    val profileSizes: List<String>
) {

    private val backdropSize : String? by lazy { getAppropriateSizeFromList(backdropSizes, BACKDROP_MAX_SIZE) }
    private val posterSize : String? by lazy { getAppropriateSizeFromList(posterSizes, POSTER_MAX_SIZE) }
    private val profileSize: String? by lazy { getAppropriateSizeFromList(profileSizes, PROFILE_MAX_SIZE) }

    fun getFullBackdropPath(path: String?) : String = "${this.secureBaseUrl}/$backdropSize/${path}"
    fun getFullPosterPath(path: String?) : String = "${this.secureBaseUrl}/$posterSize/${path}"
    fun getFullProfilePath(path: String?) : String = "${this.secureBaseUrl}/$profileSize/${path}"

    private fun getAppropriateSizeFromList(list: List<String>, size: Int): String? {
        return list.minByOrNull {
            when {
                it == ORIGINAL_SIZE -> Int.MAX_VALUE - 1
                getIntSizeFromStringWidth(it) < size -> Int.MAX_VALUE
                else -> getIntSizeFromStringWidth(it)
            }
        }
    }

    private fun getIntSizeFromStringWidth(width: String): Int = width.drop(1).toInt()

    private companion object {
        private const val BACKDROP_MAX_SIZE = 375
        private const val POSTER_MAX_SIZE = 166
        private const val PROFILE_MAX_SIZE = 80
        private const val ORIGINAL_SIZE = "original"
    }

}


