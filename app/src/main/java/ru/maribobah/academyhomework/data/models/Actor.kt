package ru.maribobah.academyhomework.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Actor (
     val name: String,
     val avatar: Int
) : Parcelable