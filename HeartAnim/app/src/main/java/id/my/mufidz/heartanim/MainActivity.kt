package id.my.mufidz.heartanim

import HeartBeatAnim
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.mufidz.heartanim.ui.theme.HeartAnimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeartAnimTheme {
                HeartBeatScreen()

            }
        }
    }
}

@Composable
fun HeartBeatScreen() {
    var bpm by remember { mutableIntStateOf(100) }
    val bpmState = BpmState(bpm)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            HeartBeatAnim(bpmState = bpmState)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "BPM Saat Ini: $bpm",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Slider(
                    value = bpm.toFloat(),
                    onValueChange = { bpm = it.toInt() },
                    valueRange = 0f..200f,
                    steps = 159,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Geser untuk menyesuaikan kecepatan detak jantung.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HeartAnimTheme {
        HeartBeatScreen()
    }
}