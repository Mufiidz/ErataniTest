package id.my.mufidz.searchword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.mufidz.searchword.ui.theme.SearchWordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchWordTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val availableWords = remember { mutableStateListOf("Ini", "adalah", "CONTOH", "sAjA") }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchView(availableWords = availableWords) {
                availableWords.add(it)
            }
            AvailableWordsView(availableWords = availableWords)
        }
    }
}

@Composable
fun SearchView(availableWords: MutableList<String>, onSaveWords: (String) -> Unit) {
    var searchWord by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var visibleOption by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Search Word",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = searchWord,
            onValueChange = {
                if (it.isEmpty() || it != searchWord) {
                    visibleOption = false
                    message = ""
                }
                searchWord = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search Word") },
            singleLine = true

        )
        Spacer(Modifier.height(16.dp))
        Text(message)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (searchWord.isEmpty()) return@Button
                result = isWordExist(availableWords, searchWord)
                message =
                    if (result)
                        "Kata \"$searchWord\" ditemukan!"
                    else "Kata \"$searchWord\" tidak ditemukan!"
                visibleOption = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = searchWord.isNotEmpty()
        ) { Text("Find") }
        Spacer(Modifier.height(16.dp))
        AnimatedVisibility(visible = visibleOption) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    searchWord = ""
                    visibleOption = false
                    message = ""
                }) { Text("Clear") }
                Button(onClick = {
                    if (searchWord.isNotEmpty()) {
                        onSaveWords(searchWord)
                    }
                    message = ""
                    visibleOption = false
                }, enabled = (searchWord.isNotEmpty() && !result)) { Text("Save") }
            }
        }
    }
}

@Composable
fun AvailableWordsView(availableWords: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Available Words",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            Modifier.fillMaxSize(),
        ) {
            itemsIndexed(availableWords) { index, word ->
                Text(
                    "${index + 1}. $word",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                )
                HorizontalDivider()
            }
        }
    }
}

fun isWordExist(words: List<String>, value: String): Boolean {
    for (word in words) {
        if (word.equals(value, ignoreCase = false)) {
            return true
        }
    }
    return false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SearchWordTheme {
        MainScreen()
    }
}