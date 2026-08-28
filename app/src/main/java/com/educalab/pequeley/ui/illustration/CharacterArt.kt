package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.educalab.pequeley.domain.model.Mood
import kotlin.math.abs

/**
 * Avatar de personaje 100% offline usando emojis: la cara (expresión según
 * `mood`) y un accesorio distintivo se dibujan sobre un círculo con la
 * paleta de color generada a partir de `paletteSeed`, así cada niño puede
 * distinguir su avatar entre varios sin depender de arte bitmap importado.
 */
@Composable
fun CharacterAvatar(
    shapeSeed: Int,
    paletteSeed: Int,
    accessorySeed: Int,
    mood: Mood = Mood.NEUTRAL,
    size: Dp = 96.dp,
    modifier: Modifier = Modifier
) {
    val palette = PaletteFactory.forSeed(paletteSeed)
    val accessory = abs(accessorySeed) % 5
    val faceEmoji = emojiForMood(mood)
    val accessoryEmoji = emojiForAccessory(accessory)

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(palette.base),
        contentAlignment = Alignment.Center
    ) {
        Text(faceEmoji, fontSize = with(LocalDensity.current) { (size.value * 0.56f).sp })
        if (accessoryEmoji != null) {
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                Text(accessoryEmoji, fontSize = with(LocalDensity.current) { (size.value * 0.34f).sp })
            }
        }
    }
}

private fun emojiForMood(mood: Mood): String = when (mood) {
    Mood.HAPPY, Mood.PROUD -> "😄"
    Mood.THINKING -> "🤔"
    Mood.SURPRISED -> "😮"
    Mood.CALM -> "😌"
    Mood.NEUTRAL -> "🙂"
}

private fun emojiForAccessory(accessory: Int): String? = when (accessory) {
    1 -> "🧢"
    2 -> "🎀"
    3 -> "🕶️"
    4 -> "🎒"
    else -> null
}
