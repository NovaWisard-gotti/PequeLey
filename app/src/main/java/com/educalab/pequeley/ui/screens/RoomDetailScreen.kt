package com.educalab.pequeley.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.*
import com.educalab.pequeley.ui.components.PequePrimaryButton
import com.educalab.pequeley.ui.components.SectionHeader
import com.educalab.pequeley.ui.illustration.GardenIllustration
import com.educalab.pequeley.ui.illustration.ObjectIllustration
import com.educalab.pequeley.ui.illustration.RoomIllustration
import com.educalab.pequeley.ui.viewmodel.RoomDetailViewModel

@Composable
fun RoomDetailScreen(
    viewModel: RoomDetailViewModel,
    onBack: () -> Unit,
    onOpenSituation: (String) -> Unit,
    onOpenStory: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
            Spacer(Modifier.width(4.dp))
            state.room?.let { room ->
                RoomIllustration(roomCode = room.code, colorHex = room.colorHex, size = 48.dp)
                Spacer(Modifier.width(8.dp))
                Text(room.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }

        if (state.loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.room?.let { room ->
                item { Text(room.description, style = MaterialTheme.typography.bodyLarge) }
            }

            if (state.garden.growthLevel >= 0 && state.room?.code == "respeto") {
                item {
                    SectionHeader("El jardín crece contigo", "Cada acción respetuosa cambia el jardín de verdad.")
                    Spacer(Modifier.height(8.dp))
                    GardenIllustration(garden = state.garden)
                }
            }

            if (state.concepts.isNotEmpty()) {
                item {
                    SectionHeader("Conceptos de esta sala")
                    Spacer(Modifier.height(8.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.concepts) { concept -> ConceptCard(concept) }
                    }
                }
            }

            if (state.situations.isNotEmpty()) {
                item { SectionHeader("Situaciones para vivir", "Observa, decide y descubre qué ocurre.") }
                items(state.situations) { situation ->
                    SituationRow(situation) { onOpenSituation(situation.code) }
                }
            }

            if (state.stories.isNotEmpty()) {
                item { SectionHeader("Historias interactivas") }
                items(state.stories) { story ->
                    StoryRow(story) { onOpenStory(story.code) }
                }
            }

            if (state.responsibilityTasks.isNotEmpty()) {
                item { SectionHeader("Objetos que necesitan cuidado") }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.responsibilityTasks) { task -> ResponsibilityCard(task) }
                    }
                }
            }

            if (state.rightLessons.isNotEmpty()) {
                item { SectionHeader("Libros de la biblioteca", "Cada libro cuenta una pequeña historia sobre un derecho.") }
                items(state.rightLessons) { lesson -> RightLessonRow(lesson) }
            }

            if (state.challenges.isNotEmpty()) {
                item { SectionHeader("Desafíos de justicia cotidiana") }
                items(state.challenges) { challenge -> ChallengeRow(challenge) }
            }

            if (state.room?.code == "acuerdos") {
                item {
                    SectionHeader("Mesa de construcción de acuerdos", "Combina acciones para construir un acuerdo sólido.")
                    Spacer(Modifier.height(8.dp))
                    AgreementBuilder(state, viewModel)
                }
                if (state.agreements.isNotEmpty()) {
                    item { SectionHeader("Mis grandes acuerdos") }
                    items(state.agreements) { agreement -> AgreementRow(agreement) }
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun ConceptCard(concept: LegalConcept) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFFFF7EC))
            .padding(14.dp)
    ) {
        ObjectIllustration(seed = concept.illustrationSeed, size = 56.dp)
        Spacer(Modifier.height(8.dp))
        Text(concept.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(concept.everydayExplanation, style = MaterialTheme.typography.bodyMedium, maxLines = 4)
    }
}

@Composable
private fun SituationRow(situation: Situation, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ObjectIllustration(seed = situation.illustrationSeed, size = 56.dp)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(situation.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(situation.summary, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
        }
    }
}

@Composable
private fun StoryRow(story: StoryModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFDF1E3))
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ObjectIllustration(seed = story.coverIllustrationSeed, size = 56.dp)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(story.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(story.summary, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
        }
    }
}

@Composable
private fun ResponsibilityCard(task: ResponsibilityTaskModel) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ObjectIllustration(seed = task.objectIllustrationSeed, size = 56.dp)
        Spacer(Modifier.height(6.dp))
        Text(task.title, style = MaterialTheme.typography.labelLarge, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Text(task.careAction, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF7A7368))
    }
}

@Composable
private fun RightLessonRow(lesson: RightLessonModel) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { expanded = !expanded }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ObjectIllustration(seed = lesson.illustrationSeed, size = 48.dp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(lesson.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(lesson.everydayExplanation, style = MaterialTheme.typography.bodyMedium)
            }
        }
        if (expanded) {
            Spacer(Modifier.height(8.dp))
            Text(lesson.storyText, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ChallengeRow(challenge: ChallengeModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF2EAF7))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ObjectIllustration(seed = challenge.difficulty * 17 + challenge.code.length, size = 44.dp)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(challenge.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(challenge.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun AgreementBuilder(state: com.educalab.pequeley.ui.viewmodel.RoomDetailState, viewModel: RoomDetailViewModel) {
    var title by remember { mutableStateOf("") }
    Column {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.agreementSymbols) { symbol ->
                val selected = state.agreementBuilderSelection.contains(symbol.code)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (selected) Color(0xFF6FCF97) else Color(0xFFEFE7DA))
                        .clickable { viewModel.toggleSymbol(symbol.code) }
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Text(symbol.label, color = if (selected) Color.White else Color(0xFF33291F), style = MaterialTheme.typography.labelLarge)
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = title, onValueChange = { title = it },
            label = { Text("Título del acuerdo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        state.agreementBuildError?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(Modifier.height(10.dp))
        PequePrimaryButton(
            text = "Construir acuerdo",
            enabled = title.isNotBlank() && state.agreementBuilderSelection.size >= 2,
            onClick = { viewModel.buildAgreement(title.trim()); title = "" },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AgreementRow(agreement: AgreementModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEAF7EF))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Check, contentDescription = null, tint = Color(0xFF6FCF97))
        Spacer(Modifier.width(10.dp))
        Column {
            Text(agreement.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(agreement.items.joinToString(" + "), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
