package id.my.mufidz.stockapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import id.my.mufidz.stockapp.model.Transaction
import id.my.mufidz.stockapp.model.TransactionType
import id.my.mufidz.stockapp.ui.theme.StockAppTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockAppTheme {
                StockApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockApp() {

    val context = LocalContext.current
    val products = remember {
        readProductFromJsonFile(context, "products.json")
    }

    val transactions = remember {
        val calendar = Calendar.getInstance()
        val transactionList = mutableListOf<Transaction>()


        val currentStock = products.associate { it.id to it.initialStock }.toMutableMap()

        var transactionId = 1


        repeat(5) {
            val randomDay = (1..28).random()
            calendar.set(Calendar.DAY_OF_MONTH, randomDay)
            calendar.set(Calendar.HOUR_OF_DAY, (8..20).random())
            calendar.set(Calendar.MINUTE, (0..59).random())

            val product = products.random()
            val productStockAvailable = currentStock[product.id] ?: 0
            val transactionType = mutableListOf(
                TransactionType.SALE, TransactionType.PURCHASE
            ).random()


            if (productStockAvailable > 0) {

                val maxQuantity = minOf(10, productStockAvailable)
                val quantity = (1..maxQuantity).random()

                transactionList.add(
                    Transaction(
                        id = transactionId++,
                        productId = product.id,
                        productName = product.name,
                        quantity = quantity,
                        date = calendar.time,
                        type = transactionType
                    )
                )

                if (transactionType == TransactionType.SALE) {
                    currentStock[product.id] = productStockAvailable - quantity
                } else {
                    currentStock[product.id] = productStockAvailable + quantity
                }
            }
        }

        transactionList.sortedByDescending { it.date }
    }

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Stock Summary", "Transaksi")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manajemen Stock Barang") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) })
                }
            }

            when (selectedTab) {
                0 -> StockSummaryScreen(products, transactions)
                1 -> TransactionListScreen(transactions)
            }
        }
    }
}