package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * Ilustración paramétrica para objetos interactivos (mochila, planta,
 * libro, botella...), insignias y decoraciones del jardín. El `seed`
 * elige un arquetipo de forma entre 10 disponibles y una paleta cálida
 * coherente con el resto de la identidad visual — así cada uno de los
 * 25+ objetos y las 12 insignias resulta visualmente distinto sin
 * depender de arte bitmap importado (ver MEMORIA_DESCRIPTIVA.md,
 * sección "Estrategia de ilustración").
 */
@Composable
fun ObjectIllustration(seed: Int, modifier: Modifier = Modifier, size: Dp = 88.dp, ribbon: Boolean = false) {
    val palette = PaletteFactory.forSeed(seed)
    val archetype = abs(seed) % 10
    Canvas(modifier = modifier.size(size)) {
        when (archetype) {
            0 -> drawBook(palette)
            1 -> drawPlant(palette)
            2 -> drawBackpack(palette)
            3 -> drawBottle(palette)
            4 -> drawBall(palette)
            5 -> drawKey(palette)
            6 -> drawHeart(palette)
            7 -> drawShield(palette)
            8 -> drawStar(palette)
            else -> drawGear(palette)
        }
        if (ribbon) drawRibbon(palette)
    }
}

private fun DrawScope.drawBook(p: Palette) {
    val w = size.width; val h = size.height
    drawRoundRect(p.base, Offset(w * 0.18f, h * 0.25f), Size(w * 0.64f, h * 0.5f), CornerRadius(w * 0.05f, w * 0.05f))
    drawLine(p.dark, Offset(w * 0.5f, h * 0.25f), Offset(w * 0.5f, h * 0.75f), strokeWidth = 3f)
}

private fun DrawScope.drawPlant(p: Palette) {
    val w = size.width; val h = size.height
    drawRoundRect(p.base, Offset(w * 0.32f, h * 0.6f), Size(w * 0.36f, h * 0.3f), CornerRadius(w * 0.04f, w * 0.04f))
    drawCircle(Color(0xFF6FCF97), w * 0.16f, Offset(w * 0.5f, h * 0.4f))
    drawCircle(Color(0xFF6FCF97), w * 0.12f, Offset(w * 0.35f, h * 0.5f))
    drawCircle(Color(0xFF6FCF97), w * 0.12f, Offset(w * 0.65f, h * 0.5f))
}

private fun DrawScope.drawBackpack(p: Palette) {
    val w = size.width; val h = size.height
    drawRoundRect(p.base, Offset(w * 0.22f, h * 0.3f), Size(w * 0.56f, h * 0.55f), CornerRadius(w * 0.16f, w * 0.16f))
    drawRoundRect(p.dark, Offset(w * 0.38f, h * 0.42f), Size(w * 0.24f, h * 0.16f), CornerRadius(w * 0.06f, w * 0.06f))
    drawRoundRect(p.accent, Offset(w * 0.32f, h * 0.16f), Size(w * 0.36f, h * 0.14f), CornerRadius(w * 0.08f, w * 0.08f))
}

private fun DrawScope.drawBottle(p: Palette) {
    val w = size.width; val h = size.height
    drawRoundRect(p.base, Offset(w * 0.36f, h * 0.3f), Size(w * 0.28f, h * 0.55f), CornerRadius(w * 0.1f, w * 0.1f))
    drawRoundRect(p.dark, Offset(w * 0.42f, h * 0.14f), Size(w * 0.16f, h * 0.18f), CornerRadius(w * 0.04f, w * 0.04f))
}

private fun DrawScope.drawBall(p: Palette) {
    val w = size.width; val h = size.height
    drawCircle(p.base, w * 0.32f, Offset(w / 2f, h / 2f))
    drawArc(p.dark, 0f, 180f, false, Offset(w * 0.18f, h * 0.18f), Size(w * 0.64f, h * 0.64f), style = Stroke(3f))
}

private fun DrawScope.drawKey(p: Palette) {
    val w = size.width; val h = size.height
    drawCircle(p.base, w * 0.16f, Offset(w * 0.32f, h * 0.4f), style = Stroke(w * 0.06f))
    drawLine(p.base, Offset(w * 0.42f, h * 0.5f), Offset(w * 0.78f, h * 0.75f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
    drawLine(p.base, Offset(w * 0.68f, h * 0.68f), Offset(w * 0.76f, h * 0.6f), strokeWidth = w * 0.05f, cap = StrokeCap.Round)
}

private fun DrawScope.drawHeart(p: Palette) {
    val w = size.width; val h = size.height
    drawCircle(p.base, w * 0.18f, Offset(w * 0.38f, h * 0.4f))
    drawCircle(p.base, w * 0.18f, Offset(w * 0.62f, h * 0.4f))
    val path = androidx.compose.ui.graphics.Path().apply {
        moveTo(w * 0.2f, h * 0.44f)
        lineTo(w * 0.5f, h * 0.8f)
        lineTo(w * 0.8f, h * 0.44f)
        close()
    }
    drawPath(path, p.base)
}

private fun DrawScope.drawShield(p: Palette) {
    val w = size.width; val h = size.height
    val path = androidx.compose.ui.graphics.Path().apply {
        moveTo(w * 0.5f, h * 0.15f)
        lineTo(w * 0.8f, h * 0.28f)
        lineTo(w * 0.8f, h * 0.55f)
        cubicTo(w * 0.8f, h * 0.78f, w * 0.65f, h * 0.88f, w * 0.5f, h * 0.92f)
        cubicTo(w * 0.35f, h * 0.88f, w * 0.2f, h * 0.78f, w * 0.2f, h * 0.55f)
        lineTo(w * 0.2f, h * 0.28f)
        close()
    }
    drawPath(path, p.base)
    drawPath(path, p.dark, style = Stroke(3f))
}

private fun DrawScope.drawStar(p: Palette) {
    val w = size.width; val h = size.height
    val cx = w / 2f; val cy = h / 2f
    val outerR = w * 0.36f; val innerR = w * 0.16f
    val path = androidx.compose.ui.graphics.Path()
    for (i in 0 until 10) {
        val r = if (i % 2 == 0) outerR else innerR
        val angle = Math.PI / 5 * i - Math.PI / 2
        val x = cx + (r * cos(angle)).toFloat()
        val y = cy + (r * sin(angle)).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, p.base)
}

private fun DrawScope.drawGear(p: Palette) {
    val w = size.width; val h = size.height
    drawCircle(p.base, w * 0.26f, Offset(w / 2f, h / 2f))
    drawCircle(Color.White, w * 0.1f, Offset(w / 2f, h / 2f))
    repeat(6) { i ->
        val angle = Math.PI / 3 * i
        val x = w / 2f + (w * 0.34f * cos(angle)).toFloat()
        val y = h / 2f + (w * 0.34f * sin(angle)).toFloat()
        drawCircle(p.base, w * 0.08f, Offset(x, y))
    }
}

private fun DrawScope.drawRibbon(p: Palette) {
    val w = size.width; val h = size.height
    drawRoundRect(p.accent, Offset(w * 0.4f, h * 0.78f), Size(w * 0.2f, h * 0.2f), CornerRadius(4f, 4f))
}
