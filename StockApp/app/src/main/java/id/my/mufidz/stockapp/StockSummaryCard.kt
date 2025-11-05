package id.my.mufidz.stockapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.mufidz.stockapp.model.StockSummary

@Composable
fun StockSummaryCard(summary: StockSummary) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = summary.productName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Stock Awal",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        "${summary.initialStock}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Terjual",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        "${summary.totalSales}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE53935)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Restocking",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        "${summary.totalPurchase}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1976D2)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        "Stock Akhir",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        "${summary.remainingStock}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (summary.remainingStock < 20) Color(0xFFE53935)
                        else Color(0xFF43A047)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { (summary.remainingStock.toFloat() / summary.initialStock.toFloat()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = if (summary.remainingStock < 20) Color(0xFFE53935)
                else Color(0xFF43A047),
                trackColor = Color.LightGray
            )

            if (summary.remainingStock < 20) {
                val emptyStock = if (summary.remainingStock == 0) "Stock Habis" else "Stock menipis"
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "⚠️ $emptyStock! Segera lakukan restocking",
                    fontSize = 12.sp,
                    color = Color(0xFFE53935),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}