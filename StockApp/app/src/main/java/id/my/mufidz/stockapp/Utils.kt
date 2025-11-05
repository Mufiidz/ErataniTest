package id.my.mufidz.stockapp

import android.content.Context
import id.my.mufidz.stockapp.model.Product
import kotlinx.serialization.json.Json

fun readProductFromJsonFile(context: Context, fileName: String) : List<Product> {
    val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    return try {
        json.decodeFromString(jsonString)
    } catch (e: Exception) {
        mutableListOf()
    }
}