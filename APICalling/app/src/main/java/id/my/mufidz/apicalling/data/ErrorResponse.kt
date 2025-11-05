package id.my.mufidz.apicalling.data

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val field: String,
    val message: String
)
