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

/**
 * Cada habitación tiene una escena visual propia y reconocible (nunca un
 * simple ícono Material). Se dibuja con Compose Canvas para funcionar
 * 100% offline y mantener coherencia con el resto de la identidad visual.
 */
@Composable
fun RoomIllustration(roomCode: String, colorHex: String, modifier: Modifier = Modifier, size: Dp = 120.dp) {
    val base = runCatching { Color(android.graphics.Color.parseColor(colorHex)) }.getOrDefault(Color(0xFFF6A93B))
    Canvas(modifier = modifier.size(size)) {
        when (roomCode) {
            "reglas" -> drawRulesScene(base)
            "derechos" -> drawRightsScene(base)
            "responsabilidades" -> drawResponsibilityScene(base)
            "acuerdos" -> drawAgreementScene(base)
            "convivencia" -> drawConvivenceScene(base)
            "decisiones" -> drawDecisionScene(base)
            "respeto" -> drawRespectScene(base)
            "historias" -> drawStoriesScene(base)
            else -> drawDefaultScene(base)
        }
    }
}

private fun DrawScope.drawRulesScene(base: Color) {
    val w = size.width; val h = size.height
    // Poste + semáforo estilizado
    drawRoundRect(Color(0xFF5B4636), Offset(w * 0.46f, h * 0.35f), Size(w * 0.08f, h * 0.55f), CornerRadius(4f, 4f))
    drawRoundRect(base, Offset(w * 0.30f, h * 0.08f), Size(w * 0.40f, h * 0.55f), CornerRadius(w * 0.12f, w * 0.12f))
    val lightColors = listOf(Color(0xFFE05B5B), Color(0xFFF2C14E), Color(0xFF6FCF97))
    lightColors.forEachIndexed { i, c ->
        drawCircle(c, radius = w * 0.07f, center = Offset(w * 0.5f, h * (0.18f + i * 0.15f)))
    }
}

private fun DrawScope.drawRightsScene(base: Color) {
    val w = size.width; val h = size.height
    // Libro abierto
    drawRoundRect(base, Offset(w * 0.10f, h * 0.35f), Size(w * 0.38f, h * 0.4f), CornerRadius(w * 0.04f, w * 0.04f))
    drawRoundRect(base.copy(alpha = 0.85f), Offset(w * 0.52f, h * 0.35f), Size(w * 0.38f, h * 0.4f), CornerRadius(w * 0.04f, w * 0.04f))
    repeat(3) { i ->
        drawLine(Color.White, Offset(w * 0.16f, h * (0.45f + i * 0.09f)), Offset(w * 0.42f, h * (0.45f + i * 0.09f)), strokeWidth = 3f, cap = StrokeCap.Round)
        drawLine(Color.White, Offset(w * 0.58f, h * (0.45f + i * 0.09f)), Offset(w * 0.84f, h * (0.45f + i * 0.09f)), strokeWidth = 3f, cap = StrokeCap.Round)
    }
}

private fun DrawScope.drawResponsibilityScene(base: Color) {
    val w = size.width; val h = size.height
    // Planta en maceta
    drawRoundRect(base, Offset(w * 0.35f, h * 0.62f), Size(w * 0.3f, h * 0.28f), CornerRadius(w * 0.04f, w * 0.04f))
    drawCircle(Color(0xFF6FCF97), w * 0.14f, Offset(w * 0.5f, h * 0.42f))
    drawCircle(Color(0xFF6FCF97), w * 0.11f, Offset(w * 0.38f, h * 0.5f))
    drawCircle(Color(0xFF6FCF97), w * 0.11f, Offset(w * 0.62f, h * 0.5f))
}

private fun DrawScope.drawAgreementScene(base: Color) {
    val w = size.width; val h = size.height
    // Mesa con dos manos que se encuentran (simplificado como dos formas que se unen)
    drawRoundRect(base, Offset(w * 0.12f, h * 0.62f), Size(w * 0.76f, h * 0.16f), CornerRadius(w * 0.04f, w * 0.04f))
    drawRoundRect(base.copy(alpha = 0.85f), Offset(w * 0.16f, h * 0.35f), Size(w * 0.3f, h * 0.18f), CornerRadius(w * 0.09f, w * 0.09f))
    drawRoundRect(base.copy(alpha = 0.6f), Offset(w * 0.54f, h * 0.35f), Size(w * 0.3f, h * 0.18f), CornerRadius(w * 0.09f, w * 0.09f))
}

private fun DrawScope.drawConvivenceScene(base: Color) {
    val w = size.width; val h = size.height
    // Dos burbujas de diálogo
    drawRoundRect(base, Offset(w * 0.08f, h * 0.18f), Size(w * 0.5f, h * 0.32f), CornerRadius(w * 0.14f, w * 0.14f))
    drawRoundRect(base.copy(alpha = 0.75f), Offset(w * 0.42f, h * 0.48f), Size(w * 0.5f, h * 0.32f), CornerRadius(w * 0.14f, w * 0.14f))
}

private fun DrawScope.drawDecisionScene(base: Color) {
    val w = size.width; val h = size.height
    // Camino que se bifurca (metáfora de decisión)
    drawLine(base, Offset(w * 0.5f, h * 0.85f), Offset(w * 0.5f, h * 0.5f), strokeWidth = w * 0.08f, cap = StrokeCap.Round)
    drawLine(base, Offset(w * 0.5f, h * 0.5f), Offset(w * 0.2f, h * 0.15f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
    drawLine(base.copy(alpha = 0.7f), Offset(w * 0.5f, h * 0.5f), Offset(w * 0.8f, h * 0.15f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
}

private fun DrawScope.drawRespectScene(base: Color) {
    val w = size.width; val h = size.height
    // Flor sencilla
    val cx = w * 0.5f; val cy = h * 0.42f
    repeat(5) { i ->
        val angle = (i * 72.0) * Math.PI / 180.0
        val px = cx + (w * 0.16f) * kotlin.math.cos(angle).toFloat()
        val py = cy + (w * 0.16f) * kotlin.math.sin(angle).toFloat()
        drawCircle(base, w * 0.12f, Offset(px, py))
    }
    drawCircle(Color(0xFFF2C14E), w * 0.1f, Offset(cx, cy))
    drawLine(Color(0xFF6FCF97), Offset(cx, cy + w * 0.16f), Offset(cx, h * 0.88f), strokeWidth = 5f)
}

private fun DrawScope.drawStoriesScene(base: Color) {
    val w = size.width; val h = size.height
    // Pila de libros
    val colors = listOf(base, base.copy(alpha = 0.8f), base.copy(alpha = 0.6f))
    colors.forEachIndexed { i, c ->
        drawRoundRect(c, Offset(w * (0.18f + i * 0.02f), h * (0.7f - i * 0.14f)), Size(w * (0.64f - i * 0.04f), h * 0.12f), CornerRadius(6f, 6f))
    }
}

private fun DrawScope.drawDefaultScene(base: Color) {
    drawCircle(base, size.minDimension * 0.3f, Offset(size.width / 2f, size.height / 2f))
}
