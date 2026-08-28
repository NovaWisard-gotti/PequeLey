package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.Mood
import com.educalab.pequeley.ui.components.PequePrimaryButton
import com.educalab.pequeley.ui.illustration.CharacterAvatar

@Composable
fun ProfileSetupScreen(onConfirm: (alias: String, avatarId: Int) -> Unit) {
    var alias by remember { mutableStateOf("") }
    var selectedAvatar by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("¿Cómo te llamamos?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Elige un alias. No necesitas tu nombre real.", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = alias,
            onValueChange = { if (it.length <= 20) alias = it },
            label = { Text("Tu alias") },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        Text("Elige tu avatar", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items((1..8).toList()) { avatarId ->
                val selected = selectedAvatar == avatarId
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .border(
                            width = if (selected) 3.dp else 0.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .clickable { selectedAvatar = avatarId }
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CharacterAvatar(
                        shapeSeed = avatarId, paletteSeed = avatarId * 3, accessorySeed = avatarId,
                        mood = Mood.HAPPY, size = 68.dp
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        PequePrimaryButton(
            text = "¡Listo!",
            enabled = alias.isNotBlank(),
            onClick = { onConfirm(alias.trim().ifEmpty { "Explorador" }, selectedAvatar) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
