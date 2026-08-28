package com.educalab.pequeley.ui.illustration

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Fachada de "La Casa de las Buenas Decisiones", usada como cabecera
 * de la Puerta Principal. Una gran casa ilustrada con tejado, ventanas
 * y una puerta central, con detalles de cielo, sol, nubes, árboles y
 * flores hechos con emoji para dar vida a la escena — la identidad
 * visual exclusiva de PequeLey (nunca un dashboard ni una ciudad
 * financiera).
 */
@Composable
fun HouseFacade(progress: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().height(220.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width; val h = size.height

            // Cielo con degradado suave
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFEF6E8), Color(0xFFFCEFD8)),
                    startY = 0f, endY = h * 0.7f
                ),
                topLeft = Offset.Zero, size = Size(w, h * 0.7f)
            )
            drawRect(Color(0xFFEAD9C2), Offset(0f, h * 0.65f), Size(w, h * 0.35f))

            // Cuerpo de la casa
            val houseLeft = w * 0.2f
            val houseTop = h * 0.35f
            val houseWidth = w * 0.6f
            val houseHeight = h * 0.45f
            drawRoundRect(Color(0xFFFFF7EC), Offset(houseLeft, houseTop), Size(houseWidth, houseHeight), CornerRadius(10f, 10f))

            // Tejado
            val roofPath = androidx.compose.ui.graphics.Path().apply {
                moveTo(houseLeft - w * 0.06f, houseTop)
                lineTo(houseLeft + houseWidth / 2f, houseTop - h * 0.22f)
                lineTo(houseLeft + houseWidth + w * 0.06f, houseTop)
                close()
            }
            drawPath(roofPath, Color(0xFF8C5E42))

            // Ventanas laterales
            drawRoundRect(Color(0xFF8FD4C1), Offset(houseLeft + houseWidth * 0.12f, houseTop + houseHeight * 0.22f), Size(houseWidth * 0.18f, houseHeight * 0.22f), CornerRadius(8f, 8f))
            drawRoundRect(Color(0xFF8FD4C1), Offset(houseLeft + houseWidth * 0.7f, houseTop + houseHeight * 0.22f), Size(houseWidth * 0.18f, houseHeight * 0.22f), CornerRadius(8f, 8f))

            // Puerta central (más grande cuanto más progreso, sugiriendo apertura/bienvenida)
            val doorWidth = houseWidth * (0.22f + progress * 0.04f)
            val doorHeight = houseHeight * 0.58f
            drawRoundRect(
                Color(0xFFF6A93B),
                Offset(houseLeft + houseWidth / 2f - doorWidth / 2f, houseTop + houseHeight - doorHeight),
                Size(doorWidth, doorHeight),
                CornerRadius(doorWidth * 0.25f, doorWidth * 0.25f)
            )
            drawCircle(Color(0xFF5B4636), 4f, Offset(houseLeft + houseWidth / 2f + doorWidth * 0.28f, houseTop + houseHeight - doorHeight * 0.45f))

            // Camino hacia la puerta
            drawRoundRect(Color(0xFFE8D9B5), Offset(houseLeft + houseWidth / 2f - doorWidth * 0.6f, houseTop + houseHeight), Size(doorWidth * 1.2f, h * 0.06f), CornerRadius(6f, 6f))
        }

        Text("☀️", fontSize = 28.sp, modifier = Modifier.align(Alignment.TopStart).padding(start = 18.dp, top = 10.dp))
        Text("☁️", fontSize = 20.sp, modifier = Modifier.align(Alignment.TopEnd).padding(end = 54.dp, top = 22.dp))
        Text("☁️", fontSize = 16.sp, modifier = Modifier.align(Alignment.TopCenter).padding(top = 6.dp))
        Text("🌳", fontSize = 34.sp, modifier = Modifier.align(Alignment.BottomStart).padding(start = 6.dp, bottom = 22.dp))
        Text("🌳", fontSize = 34.sp, modifier = Modifier.align(Alignment.BottomEnd).padding(end = 6.dp, bottom = 22.dp))
        Text("🌷", fontSize = 18.sp, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 6.dp))
    }
}
