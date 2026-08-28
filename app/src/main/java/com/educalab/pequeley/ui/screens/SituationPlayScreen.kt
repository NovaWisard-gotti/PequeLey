package com.educalab.pequeley.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.DecisionModel
import com.educalab.pequeley.domain.model.StepType
import com.educalab.pequeley.ui.components.PequePrimaryButton
import com.educalab.pequeley.ui.components.StarProgressBar
import com.educalab.pequeley.ui.illustration.ObjectIllustration
import com.educalab.pequeley.ui.viewmodel.SituationPlayViewModel

@Composable
fun SituationPlayScreen(viewModel: SituationPlayViewModel, onBack: () -> Unit, onFinished: () -> Unit) {
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
            state.session?.let { session ->
                Spacer(Modifier.width(4.dp))
                Text(session.situation.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }

        val session = state.session
        if (state.loading || session == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            return
        }

        Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            StarProgressBar(ratio = com.educalab.pequeley.domain.engine.SituationEngine().progressRatio(session), totalStars = session.totalSteps.coerceAtLeast(1))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!session.finished) {
                state.lastConsequenceText?.let { text ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFFF7EC))
                            .padding(12.dp)
                    ) {
                        Text(text, style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(Modifier.height(16.dp))
                }

                val step = session.currentStep
                if (step != null) {
                    ObjectIllustration(seed = step.illustrationSeed, size = 110.dp)
                    Spacer(Modifier.height(16.dp))
                    Text(step.prompt, style = MaterialTheme.typography.headlineMedium, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Spacer(Modifier.height(24.dp))

                    when (step.stepType) {
                        StepType.DECISION -> {
                            step.decisions.forEach { decision ->
                                DecisionOption(decision) { viewModel.choose(decision) }
                                Spacer(Modifier.height(12.dp))
                            }
                        }
                        else -> {
                            Spacer(Modifier.weight(1f))
                            PequePrimaryButton(text = "Continuar", onClick = { viewModel.advance() }, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            } else {
                Spacer(Modifier.height(24.dp))
                Text("¡Historia completada!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                state.lastConsequenceText?.let {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(Color(0xFFFFF7EC))
                            .padding(16.dp)
                    ) {
                        Text(it, style = MaterialTheme.typography.bodyLarge, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    }
                }
                Spacer(Modifier.weight(1f))
                if (state.newBadges.isNotEmpty()) {
                    Text("¡Nueva insignia desbloqueada: ${state.newBadges.first().title}!", style = MaterialTheme.typography.titleMedium, color = Color(0xFFE07A5F))
                    Spacer(Modifier.height(12.dp))
                }
                PequePrimaryButton(text = "Volver a la sala", onClick = onFinished, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun DecisionOption(decision: DecisionModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(decision.label, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(decision.description, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF7A7368))
    }
}
