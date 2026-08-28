package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

/**
 * Ilustración de objetos interactivos (mochila, planta, libro, botella...),
 * insignias y decoraciones del jardín usando emojis sobre un fondo circular
 * con la paleta cálida de PequeLey — claros y reconocibles para un niño,
 * sin depender de arte bitmap importado. El `seed` elige un emoji entre 10
 * arquetipos disponibles de forma determinística, así cada objeto/insignia
 * mantiene siempre la misma apariencia.
 */
@Composable
fun ObjectIllustration(seed: Int, modifier: Modifier = Modifier, size: Dp = 88.dp, ribbon: Boolean = false) {
    val palette = PaletteFactory.forSeed(seed)
    val archetype = abs(seed) % 10
    val emoji = emojiForArchetype(archetype)

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(palette.light),
        contentAlignment = Alignment.Center
    ) {
        Text(emoji, fontSize = with(LocalDensity.current) { (size.value * 0.5f).sp })
        if (ribbon) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-2).dp, y = (-2).dp)
                    .size(size * 0.36f)
                    .clip(CircleShape)
                    .background(palette.accent),
                contentAlignment = Alignment.Center
            ) {
                Text("🎗️", fontSize = with(LocalDensity.current) { (size.value * 0.2f).sp })
            }
        }
    }
}

private fun emojiForArchetype(archetype: Int): String = when (archetype) {
    0 -> "📘" // libro
    1 -> "🪴" // planta
    2 -> "🎒" // mochila
    3 -> "🧴" // botella
    4 -> "⚽" // pelota
    5 -> "🔑" // llave
    6 -> "❤️" // corazón
    7 -> "🛡️" // escudo
    8 -> "⭐" // estrella
    else -> "⚙️" // engranaje
}
