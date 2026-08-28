package com.educalab.pequeley.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.HouseRoom
import com.educalab.pequeley.domain.model.RoomModuleState
import com.educalab.pequeley.ui.illustration.RoomIllustration
import com.educalab.pequeley.ui.theme.PequeBrown
import com.educalab.pequeley.ui.theme.PequeCream

/** Tarjeta-puerta de una habitación: el elemento base de navegación de la casa. */
@Composable
fun RoomDoorCard(room: HouseRoom, state: RoomModuleState, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val enabled = state != RoomModuleState.LOCKED
    val scale by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.97f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "doorScale"
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else 0.55f)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .border(2.dp, Color(android.graphics.Color.parseColor(room.colorHex)).copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            RoomIllustration(roomCode = room.code, colorHex = room.colorHex, size = 84.dp)
            if (!enabled) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(50))
                        .background(PequeBrown)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Lock, contentDescription = "Bloqueada", tint = PequeCream, modifier = Modifier.size(16.dp))
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(room.name, style = MaterialTheme.typography.titleMedium, textAlign = androidx.compose.ui.text.style.TextAlign.Center, maxLines = 2)
        Spacer(Modifier.height(4.dp))
        StateChip(state)
    }
}

@Composable
fun StateChip(state: RoomModuleState) {
    val (label, color) = when (state) {
        RoomModuleState.LOCKED -> "Bloqueada" to Color(0xFF9E9E9E)
        RoomModuleState.AVAILABLE -> "Disponible" to Color(0xFF5B8DEF)
        RoomModuleState.STARTED -> "Iniciada" to Color(0xFFF2C14E)
        RoomModuleState.COMPLETED -> "Completada" to Color(0xFF6FCF97)
        RoomModuleState.MASTERED -> "¡Dominada!" to Color(0xFFE07A5F)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.16f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(label, color = color, style = MaterialTheme.typography.labelLarge)
    }
}

/** Barra de progreso ilustrada con estrellas en vez de una barra Material genérica. */
@Composable
fun StarProgressBar(ratio: Float, modifier: Modifier = Modifier, totalStars: Int = 5) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(totalStars) { i ->
            val filled = (i + 1) <= (ratio * totalStars).let { kotlin.math.ceil(it) }
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = if (filled) Color(0xFFF2C14E) else Color(0xFFE0E0E0),
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

/** Burbuja de diálogo de Lexi: mensajes breves, nunca párrafos largos. */
@Composable
fun LexiBubble(text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        com.educalab.pequeley.ui.illustration.CharacterAvatar(
            shapeSeed = 1, paletteSeed = 1, accessorySeed = 0,
            mood = com.educalab.pequeley.domain.model.Mood.HAPPY, size = 48.dp
        )
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE8D9B5), RoundedCornerShape(topStart = 4.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .padding(12.dp)
        ) {
            Text(text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String? = null, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        if (subtitle != null) {
            Spacer(Modifier.height(2.dp))
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF7A7368))
        }
    }
}

@Composable
fun PequePrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.height(52.dp)
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium, color = Color.White)
    }
}
