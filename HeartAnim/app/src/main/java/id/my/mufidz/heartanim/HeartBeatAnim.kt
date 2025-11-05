@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import id.my.mufidz.heartanim.BpmState

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun HeartBeatAnim(bpmState: BpmState) {

    var scale by remember { mutableFloatStateOf(1.0f) }

    val minBpm = 40f
    val maxBpm = 200f
    val minScale = 1.05f
    val maxScale = 1.25f

    val targetScale = if (bpmState.bpm > 0) {

        val normalizedBpm = ((bpmState.bpm.toFloat() - minBpm) / (maxBpm - minBpm)).coerceIn(0f, 1f)


        maxScale - (normalizedBpm * (maxScale - minScale))
    } else {
        1.0f
    }

    if (bpmState.bpm > 0) {
        val infiniteTransition = rememberInfiniteTransition(label = "HeartBeatTransition")

        val animatedScale by infiniteTransition.animateFloat(
            initialValue = 1.0f, targetValue = targetScale, animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = bpmState.halfDurationMs, easing = LinearEasing
                ), repeatMode = RepeatMode.Reverse
            ), label = "HeartScale"
        )
        scale = animatedScale
    } else {

        scale = 1.0f
    }

    BoxWithConstraints(
        modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center
    ) {
        val heartColor = Color.Red

        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            scale(scale = scale, pivot = Offset(canvasWidth / 2f, canvasHeight / 2f)) {

                val path = Path().apply {
                    val x = canvasWidth / 2
                    val y = canvasHeight / 2
                    val heartFactor = 0.45f * x

                    moveTo(x, y - heartFactor)
                    cubicTo(
                        x + heartFactor * 2,
                        y - heartFactor * 3,
                        x + heartFactor * 3,
                        y + heartFactor * 1,
                        x,
                        y + heartFactor * 4
                    )
                    cubicTo(
                        x - heartFactor * 3,
                        y + heartFactor * 1,
                        x - heartFactor * 2,
                        y - heartFactor * 3,
                        x,
                        y - heartFactor
                    )
                    close()
                }

                drawPath(path = path, color = heartColor)
            }
        }
    }
}