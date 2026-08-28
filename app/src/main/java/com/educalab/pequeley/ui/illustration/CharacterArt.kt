package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.Mood
import kotlin.math.abs

/**
 * Dibuja un personaje de forma completamente paramétrica usando
 * Compose Canvas: sin bitmaps externos, sin conexión a internet.
 * shapeSeed decide la forma de la cabeza y el cuerpo, paletteSeed
 * decide los colores, accessorySeed decide un accesorio distintivo
 * (gorro, bandana, lentes, mochila o insignia) y mood cambia la cara.
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
    val headShape = abs(shapeSeed) % 3 // 0 redonda, 1 ovalada, 2 cuadrada suave
    val accessory = abs(accessorySeed) % 5 // 0 ninguno, 1 gorro, 2 bandana, 3 lentes, 4 insignia

    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val cx = w / 2f
        val cy = h * 0.42f
        val headRadius = w * 0.30f

        drawRoundRect(
            color = palette.base,
            topLeft = Offset(w * 0.22f, h * 0.58f),
            size = Size(w * 0.56f, h * 0.4f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.18f, w * 0.18f)
        )

        when (headShape) {
            0 -> drawCircle(color = palette.skin, radius = headRadius, center = Offset(cx, cy))
            1 -> drawOval(
                color = palette.skin,
                topLeft = Offset(cx - headRadius * 0.85f, cy - headRadius * 1.05f),
                size = Size(headRadius * 1.7f, headRadius * 2.1f)
            )
            else -> drawRoundRect(
                color = palette.skin,
                topLeft = Offset(cx - headRadius, cy - headRadius),
                size = Size(headRadius * 2f, headRadius * 2f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(headRadius * 0.55f, headRadius * 0.55f)
            )
        }

        drawFace(cx, cy, headRadius, mood, palette)

        when (accessory) {
            1 -> drawRoundRect(
                color = palette.accent,
                topLeft = Offset(cx - headRadius, cy - headRadius * 1.55f),
                size = Size(headRadius * 2f, headRadius * 0.9f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(headRadius * 0.5f, headRadius * 0.5f)
            )
            2 -> drawRoundRect(
                color = palette.accent,
                topLeft = Offset(cx - headRadius, cy - headRadius * 0.25f),
                size = Size(headRadius * 2f, headRadius * 0.45f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(headRadius * 0.2f, headRadius * 0.2f)
            )
            3 -> {
                drawCircle(Color.White, radius = headRadius * 0.32f, center = Offset(cx - headRadius * 0.42f, cy - headRadius * 0.05f), style = Stroke(width = 3f))
                drawCircle(Color.White, radius = headRadius * 0.32f, center = Offset(cx + headRadius * 0.42f, cy - headRadius * 0.05f), style = Stroke(width = 3f))
                drawLine(Color.White, Offset(cx - headRadius * 0.1f, cy - headRadius * 0.05f), Offset(cx + headRadius * 0.1f, cy - headRadius * 0.05f), strokeWidth = 3f)
            }
            4 -> drawCircle(
                color = palette.light,
                radius = w * 0.06f,
                center = Offset(w * 0.38f, h * 0.68f)
            )
            else -> Unit
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawFace(
    cx: Float, cy: Float, r: Float, mood: Mood, palette: Palette
) {
    val eyeOffsetX = r * 0.38f
    val eyeY = cy - r * 0.05f
    val eyeRadius = r * 0.09f

    when (mood) {
        Mood.SURPRISED -> {
            drawCircle(palette.dark, eyeRadius * 1.3f, Offset(cx - eyeOffsetX, eyeY))
            drawCircle(palette.dark, eyeRadius * 1.3f, Offset(cx + eyeOffsetX, eyeY))
        }
        else -> {
            drawCircle(palette.dark, eyeRadius, Offset(cx - eyeOffsetX, eyeY))
            drawCircle(palette.dark, eyeRadius, Offset(cx + eyeOffsetX, eyeY))
        }
    }

    val mouthY = cy + r * 0.42f
    when (mood) {
        Mood.HAPPY, Mood.PROUD -> drawArc(
            color = palette.dark,
            startAngle = 10f, sweepAngle = 160f, useCenter = false,
            topLeft = Offset(cx - r * 0.35f, mouthY - r * 0.25f),
            size = Size(r * 0.7f, r * 0.5f),
            style = Stroke(width = 4f, cap = StrokeCap.Round)
        )
        Mood.THINKING -> drawLine(
            color = palette.dark,
            start = Offset(cx - r * 0.15f, mouthY),
            end = Offset(cx + r * 0.2f, mouthY - r * 0.05f),
            strokeWidth = 4f, cap = StrokeCap.Round
        )
        Mood.CALM -> drawLine(
            color = palette.dark,
            start = Offset(cx - r * 0.2f, mouthY),
            end = Offset(cx + r * 0.2f, mouthY),
            strokeWidth = 4f, cap = StrokeCap.Round
        )
        Mood.SURPRISED -> drawCircle(palette.dark, r * 0.1f, Offset(cx, mouthY))
        Mood.NEUTRAL -> drawLine(
            color = palette.dark,
            start = Offset(cx - r * 0.18f, mouthY),
            end = Offset(cx + r * 0.18f, mouthY),
            strokeWidth = 4f, cap = StrokeCap.Round
        )
    }
}
