package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.StoryChoiceModel
import com.educalab.pequeley.ui.components.PequePrimaryButton
import com.educalab.pequeley.ui.illustration.ObjectIllustration
import com.educalab.pequeley.ui.viewmodel.StoryPlayViewModel

@Composable
fun StoryPlayScreen(viewModel: StoryPlayViewModel, onBack: () -> Unit, onFinished: () -> Unit) {
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
            state.session?.let {
                Spacer(Modifier.width(4.dp))
                Text(it.story.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }

        val session = state.session
        if (state.loading || session == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            return
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!session.finished) {
                val scene = session.currentScene
                if (scene != null) {
                    ObjectIllustration(seed = scene.illustrationSeed, size = 110.dp)
                    Spacer(Modifier.height(16.dp))
                    Text(scene.text, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(24.dp))
                    scene.choices.forEach { choice ->
                        StoryChoiceCard(choice) { viewModel.choose(choice) }
                        Spacer(Modifier.height(12.dp))
                    }
                }
            } else {
                Text("Fin de la historia", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Spacer(Modifier.weight(1f))
                if (state.newBadges.isNotEmpty()) {
                    Text("¡Nueva insignia: ${state.newBadges.first().title}!", color = Color(0xFFE07A5F), style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(12.dp))
                }
                PequePrimaryButton(text = "Volver al archivo", onClick = onFinished, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun StoryChoiceCard(choice: StoryChoiceModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFDF1E3))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(choice.label, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}
