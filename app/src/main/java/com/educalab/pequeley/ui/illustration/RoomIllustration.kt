package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Cada habitación tiene un emoji propio y reconocible sobre una placa
 * redondeada con su color distintivo (nunca un simple ícono Material ni
 * formas geométricas abstractas) — funciona 100% offline.
 */
@Composable
fun RoomIllustration(roomCode: String, colorHex: String, modifier: Modifier = Modifier, size: Dp = 120.dp) {
    val base = runCatching { Color(android.graphics.Color.parseColor(colorHex)) }.getOrDefault(Color(0xFFF6A93B))
    val emoji = emojiForRoom(roomCode)
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.28f))
            .background(base.copy(alpha = 0.22f)),
        contentAlignment = Alignment.Center
    ) {
        Text(emoji, fontSize = with(LocalDensity.current) { (size.value * 0.52f).sp })
    }
}

private fun emojiForRoom(roomCode: String): String = when (roomCode) {
    "reglas" -> "🚦"
    "derechos" -> "📚"
    "responsabilidades" -> "🪴"
    "acuerdos" -> "🤝"
    "convivencia" -> "🧩"
    "decisiones" -> "🔀"
    "respeto" -> "🌸"
    "historias" -> "📖"
    else -> "✨"
}
