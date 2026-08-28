package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.ui.viewmodel.AppViewModel

@Composable
fun SettingsScreen(viewModel: AppViewModel, onBack: () -> Unit) {
    val state by viewModel.uiState.collectAsState()
    val profile = state.profile

    Column(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
            Text("Ajustes", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        if (profile != null) {
            Column(Modifier.padding(20.dp)) {
                SettingRow(
                    title = "Sonido",
                    subtitle = "Efectos de confirmación y recompensas",
                    checked = profile.soundEnabled,
                    onCheckedChange = { viewModel.setSoundEnabled(it) }
                )
                Spacer(Modifier.height(16.dp))
                SettingRow(
                    title = "Vibración",
                    subtitle = "Retroalimentación táctil ligera",
                    checked = profile.hapticEnabled,
                    onCheckedChange = { viewModel.setHapticEnabled(it) }
                )
                Spacer(Modifier.height(28.dp))
                Text("Privacidad", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                Text(
                    "PequeLey funciona sin conexión a internet. No se solicitan datos personales reales: solo un alias y un avatar, guardados únicamente en este dispositivo.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun SettingRow(title: String, subtitle: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
