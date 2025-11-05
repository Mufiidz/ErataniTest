package id.my.mufidz.stockapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import id.my.mufidz.stockapp.model.Product
import id.my.mufidz.stockapp.model.StockSummary
import id.my.mufidz.stockapp.model.Transaction
import id.my.mufidz.stockapp.model.TransactionType
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun StockSummaryScreen(products: List<Product>, transactions: List<Transaction>) {
    val configuration = LocalConfiguration.current
    val stockSummaries = remember(products, transactions) {
        products.map { product ->
            val totalSales =
                transactions.filter { it.productId == product.id && it.type == TransactionType.SALE }
                    .sumOf { it.quantity }
            val totalPurchases = transactions
                .filter { it.productId == product.id && it.type == TransactionType.PURCHASE }
                .sumOf { it.quantity }

            val remainingStock = product.initialStock - totalSales + totalPurchases

            StockSummary(
                productId = product.id,
                productName = product.name,
                initialStock = product.initialStock,
                totalSales = totalSales,
                totalPurchase = totalPurchases,
                remainingStock = remainingStock,
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Ringkasan Stock Akhir Bulan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Periode: ${
                        SimpleDateFormat(
                            "MMMM yyyy", ConfigurationCompat.getLocales(configuration).get(0)
                        ).format(Date())
                    }",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(stockSummaries) { summary ->
                StockSummaryCard(summary)
            }
        }
    }
}