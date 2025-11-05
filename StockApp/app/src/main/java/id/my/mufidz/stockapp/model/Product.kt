package id.my.mufidz.stockapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val initialStock: Int,
    val category: String,
    val price: Float
)