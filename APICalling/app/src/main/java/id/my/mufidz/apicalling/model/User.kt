package id.my.mufidz.apicalling.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val id: Int?,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
) : Parcelable
