package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.BadgeModel
import com.educalab.pequeley.ui.illustration.ObjectIllustration
import com.educalab.pequeley.ui.viewmodel.AppViewModel

@Composable
fun BadgesScreen(viewModel: AppViewModel, onBack: () -> Unit) {
    val state by viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
            Text("Mis insignias", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        val earnedCount = state.badges.count { it.earned }
        Text(
            "$earnedCount de ${state.badges.size} conseguidas",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(state.badges) { badge -> BadgeCard(badge) }
        }
    }
}

@Composable
private fun BadgeCard(badge: BadgeModel) {
    Column(
        modifier = Modifier
            .alpha(if (badge.earned) 1f else 0.4f)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ObjectIllustration(seed = badge.illustrationSeed, size = 64.dp, ribbon = true)
        Spacer(Modifier.height(8.dp))
        Text(badge.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(badge.description, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
}
