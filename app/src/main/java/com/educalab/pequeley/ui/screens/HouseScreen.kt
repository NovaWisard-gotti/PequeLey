package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.HouseRoom
import com.educalab.pequeley.domain.model.Mood
import com.educalab.pequeley.domain.model.RoomModuleState
import com.educalab.pequeley.ui.components.LexiBubble
import com.educalab.pequeley.ui.components.RoomDoorCard
import com.educalab.pequeley.ui.components.StarProgressBar
import com.educalab.pequeley.ui.illustration.CharacterAvatar
import com.educalab.pequeley.ui.illustration.HouseFacade
import com.educalab.pequeley.ui.viewmodel.AppViewModel

@Composable
fun HouseScreen(
    viewModel: AppViewModel,
    onOpenRoom: (String) -> Unit,
    onOpenBadges: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    if (state.loading || state.profile == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        return
    }

    val profile = state.profile!!
    var lockedRoomInfo by remember { mutableStateOf<HouseRoom?>(null) }
    val lexiMessage = remember(state.houseProgress) {
        when {
            state.houseProgress < 0.2f -> "Elige una puerta y descubramos qué historia hay detrás."
            state.houseProgress < 0.6f -> "¡Vas muy bien! Cada decisión que tomas hace crecer la casa."
            else -> "¡La casa está floreciendo gracias a ti! Sigamos explorando."
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            HouseFacade(progress = state.houseProgress)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(2.dp)
                    ) {
                        CharacterAvatar(
                            shapeSeed = profile.avatarId, paletteSeed = profile.avatarId * 3,
                            accessorySeed = profile.avatarId, mood = Mood.HAPPY, size = 48.dp
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(profile.alias, style = MaterialTheme.typography.titleMedium, color = Color(0xFF33291F), fontWeight = FontWeight.Bold)
                        Text("Nivel ${profile.currentLevel}", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF7A7368))
                    }
                }
                Row {
                    IconButton(onClick = onOpenBadges) {
                        Icon(Icons.Filled.EmojiEvents, contentDescription = "Insignias", tint = Color(0xFF33291F))
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Filled.Settings, contentDescription = "Ajustes", tint = Color(0xFF33291F))
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            LexiBubble(text = lexiMessage)
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Progreso de la casa", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.width(8.dp))
                StarProgressBar(ratio = state.houseProgress)
            }
        }

        Text(
            "La Casa de las Buenas Decisiones",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(state.rooms.sortedBy { it.orderIndex }) { room: HouseRoom ->
                val roomState = viewModel.stateForRoom(room)
                RoomDoorCard(
                    room = room,
                    state = roomState,
                    onClick = {
                        if (roomState == RoomModuleState.LOCKED) {
                            lockedRoomInfo = room
                        } else {
                            onOpenRoom(room.code)
                        }
                    }
                )
            }
        }
    }

    lockedRoomInfo?.let { room ->
        val pending = viewModel.pendingPrerequisitesFor(room)
        AlertDialog(
            onDismissRequest = { lockedRoomInfo = null },
            title = { Text("${room.name} está bloqueada") },
            text = {
                Text(
                    if (pending.isEmpty()) {
                        "¡Ya casi! Vuelve a jugar cualquier situación para terminar de desbloquear esta sala."
                    } else {
                        "Para abrir esta sala primero completa: ${pending.joinToString(", ") { it.name }}."
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = { lockedRoomInfo = null }) { Text("Entendido") }
            }
        )
    }
}
