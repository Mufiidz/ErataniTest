package id.my.mufidz.stockapp.model

import java.util.Date

data class Transaction(
    val id: Int,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val date: Date,
    val type: TransactionType
)

enum class TransactionType { PURCHASE, SALE }