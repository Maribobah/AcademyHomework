package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ConfigurationImage(
    @SerialName("secure_base_url")
    val secureBaseUrl: String,
    @SerialName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerialName("poster_sizes")
    val posterSizes: List<String>,
    @SerialName("profile_sizes")
    val profileSizes: List<String>
)


