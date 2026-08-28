package com.educalab.pequeley.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.educalab.pequeley.domain.model.Mood
import com.educalab.pequeley.ui.components.PequePrimaryButton
import com.educalab.pequeley.ui.illustration.CharacterAvatar
import com.educalab.pequeley.ui.illustration.HouseFacade
import kotlinx.coroutines.launch

private data class OnboardPage(val title: String, val body: String, val mood: Mood)

private val PAGES = listOf(
    OnboardPage("Bienvenido a PequeLey", "Una gran casa llena de historias te está esperando. Cada habitación esconde algo por descubrir.", Mood.HAPPY),
    OnboardPage("Conoce a Lexi", "Lexi es tu guía. Le encanta escuchar, hacer preguntas y ayudarte a encontrar buenas soluciones.", Mood.NEUTRAL),
    OnboardPage("Explora, decide y aprende", "En cada habitación vivirás pequeñas historias. Tus decisiones cambian lo que pasa después.", Mood.THINKING),
    OnboardPage("Todo queda guardado", "Tu progreso se guarda en este dispositivo. No necesitas internet ni tu nombre real: elige un alias y un avatar.", Mood.PROUD)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { PAGES.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        if (pagerState.currentPage == 0) {
            HouseFacade(progress = 0f, modifier = Modifier.padding(bottom = 8.dp))
        }
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            val item = PAGES[page]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CharacterAvatar(shapeSeed = 1, paletteSeed = 1, accessorySeed = 0, mood = item.mood, size = 120.dp)
                Spacer(Modifier.height(20.dp))
                Text(item.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(Modifier.height(12.dp))
                Text(item.body, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(PAGES.size) { i ->
                val active = pagerState.currentPage == i
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (active) 10.dp else 8.dp)
                        .then(Modifier)
                        .background(
                            color = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
            }
        }
        PequePrimaryButton(
            text = if (pagerState.currentPage == PAGES.lastIndex) "Entrar a la casa" else "Siguiente",
            onClick = {
                if (pagerState.currentPage == PAGES.lastIndex) {
                    onFinished()
                } else {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = tween(300)) }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
