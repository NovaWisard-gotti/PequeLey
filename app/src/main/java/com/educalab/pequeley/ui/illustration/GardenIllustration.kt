package com.educalab.pequeley.ui.illustration

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.GardenState

/**
 * El Jardín del Respeto (Módulo 7): NO es una barra de progreso.
 * Cada acción positiva del niño (flores/caminos/animales) modifica
 * visualmente la escena, mostrando crecimiento real y acumulado.
 */
@Composable
fun GardenIllustration(garden: GardenState, modifier: Modifier = Modifier) {
    val animatedGrowth by animateFloatAsState(
        targetValue = garden.growthLevel / 10f,
        animationSpec = tween(600),
        label = "gardenGrowth"
    )
    Canvas(modifier = modifier.fillMaxWidth().height(180.dp)) {
        val w = size.width; val h = size.height

        // Cielo y pasto base
        drawRect(Color(0xFFEFF7EE), Offset.Zero, Size(w, h * 0.75f))
        drawRect(Color(0xFF9AD8A0), Offset(0f, h * 0.72f), Size(w, h * 0.28f))

        // Caminos (según garden.paths)
        repeat(garden.paths.coerceAtMost(6)) { i ->
            drawRoundRect(
                Color(0xFFE8D9B5),
                Offset(w * (0.1f + i * 0.14f), h * 0.78f),
                Size(w * 0.08f, h * 0.16f),
                CornerRadius(6f, 6f)
            )
        }

        // Flores (según garden.flowers)
        repeat(garden.flowers.coerceAtMost(10)) { i ->
            val fx = w * (0.08f + (i % 5) * 0.19f)
            val fy = h * (0.6f - (i / 5) * 0.14f)
            drawFlower(fx, fy, w * 0.045f, PaletteFactory.forSeed(i + 3).base)
        }

        // Animalitos sencillos (según garden.animals) — círculos con orejas
        repeat(garden.animals.coerceAtMost(6)) { i ->
            val ax = w * (0.15f + i * 0.15f)
            val ay = h * 0.68f
            drawCircle(Color(0xFFF6D2B5), w * 0.04f, Offset(ax, ay))
            drawCircle(Color(0xFFF6D2B5), w * 0.018f, Offset(ax - w * 0.03f, ay - w * 0.03f))
            drawCircle(Color(0xFFF6D2B5), w * 0.018f, Offset(ax + w * 0.03f, ay - w * 0.03f))
        }

        // Sol que crece de tamaño con el progreso general del jardín
        drawCircle(Color(0xFFF2C14E), w * (0.06f + animatedGrowth * 0.05f), Offset(w * 0.85f, h * 0.15f))
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawFlower(x: Float, y: Float, r: Float, color: Color) {
    repeat(5) { i ->
        val angle = (i * 72.0) * Math.PI / 180.0
        val px = x + (r * kotlin.math.cos(angle)).toFloat()
        val py = y + (r * kotlin.math.sin(angle)).toFloat()
        drawCircle(color, r * 0.7f, Offset(px, py))
    }
    drawCircle(Color(0xFFFFE9A8), r * 0.6f, Offset(x, y))
    drawLine(Color(0xFF6FCF97), Offset(x, y + r * 0.5f), Offset(x, y + r * 2.2f), strokeWidth = 4f)
}
