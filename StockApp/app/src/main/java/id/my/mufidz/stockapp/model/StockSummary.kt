package id.my.mufidz.stockapp.model

data class StockSummary(
    val productId: Int,
    val productName: String,
    val initialStock: Int,
    val totalSales: Int,
    val totalPurchase: Int,
    val remainingStock: Int,
)