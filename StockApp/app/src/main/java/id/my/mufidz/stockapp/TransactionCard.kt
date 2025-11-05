package id.my.mufidz.stockapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import id.my.mufidz.stockapp.model.Transaction
import id.my.mufidz.stockapp.model.TransactionType
import java.text.SimpleDateFormat

@Composable
fun TransactionCard(transaction: Transaction) {
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.productName, fontSize = 14.sp, fontWeight = FontWeight.Medium
                )
                Text(
                    text = SimpleDateFormat(
                        "dd MMM yyyy, HH:mm", ConfigurationCompat.getLocales(configuration).get(0)
                    ).format(transaction.date), fontSize = 12.sp, color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFFEBEE), shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (transaction.type == TransactionType.SALE) "-${transaction.quantity}"
                    else "${transaction.quantity}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.type == TransactionType.SALE) Color(
                        0xFFE53935
                    ) else Color(0xFF4CAF50)
                )
            }
        }
    }
}