package com.educalab.pequeley.ui.illustration

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educalab.pequeley.domain.model.GardenState

/**
 * El Jardín del Respeto (Módulo 7): NO es una barra de progreso.
 * Cada acción positiva del niño (flores/caminos/animales) modifica
 * visualmente la escena usando emojis reconocibles, mostrando crecimiento
 * real y acumulado en vez de formas geométricas abstractas.
 */
@Composable
fun GardenIllustration(garden: GardenState, modifier: Modifier = Modifier) {
    val animatedGrowth by animateFloatAsState(
        targetValue = garden.growthLevel / 10f,
        animationSpec = tween(600),
        label = "gardenGrowth"
    )
    val isEmpty = garden.flowers == 0 && garden.paths == 0 && garden.animals == 0

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().weight(0.62f).background(Color(0xFFEFF7EE)))
            Box(Modifier.fillMaxWidth().weight(0.38f).background(Color(0xFF9AD8A0)))
        }

        Text(
            "☀️",
            fontSize = (20 + animatedGrowth * 18).sp,
            modifier = Modifier.align(Alignment.TopEnd).padding(12.dp)
        )

        if (isEmpty) {
            Text(
                "🌱 Aquí crecerá tu jardín con cada buena decisión",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 24.dp)
            )
        } else {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmojiWrapRows(emojiSequence(garden.flowers, FLOWER_EMOJIS))
                Spacer(Modifier.height(2.dp))
                EmojiWrapRows(emojiSequence(garden.animals, ANIMAL_EMOJIS))
                Spacer(Modifier.height(2.dp))
                EmojiWrapRows(emojiSequence(garden.paths, PATH_EMOJIS))
            }
        }
    }
}

private val FLOWER_EMOJIS = listOf("🌸", "🌼", "🌻", "🌷", "🌺")
private val ANIMAL_EMOJIS = listOf("🐰", "🐦", "🦋", "🐿️", "🐝")
private val PATH_EMOJIS = listOf("🟫")

private fun emojiSequence(count: Int, palette: List<String>): List<String> =
    (0 until count.coerceIn(0, 15)).map { palette[it % palette.size] }

@Composable
private fun EmojiWrapRows(emojis: List<String>, modifier: Modifier = Modifier) {
    if (emojis.isEmpty()) return
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        emojis.chunked(6).forEach { chunk ->
            Row {
                chunk.forEach { emoji -> Text(emoji, fontSize = 24.sp, modifier = Modifier.padding(horizontal = 2.dp)) }
            }
        }
    }
}
