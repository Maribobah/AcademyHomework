package ru.maribobah.academyhomework.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ConfigurationInfo (
    @SerialName("images")
    val images: ConfigurationImage
)