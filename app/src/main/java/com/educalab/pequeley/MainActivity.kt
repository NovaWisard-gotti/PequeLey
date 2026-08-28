package com.educalab.pequeley

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.educalab.pequeley.ui.navigation.PequeLeyNavGraph
import com.educalab.pequeley.ui.theme.PequeLeyTheme
import com.educalab.pequeley.util.OnboardingPrefs
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as PequeLeyApplication

        setContent {
            PequeLeyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var onboardingDone by remember { mutableStateOf<Boolean?>(null) }

                    LaunchedEffect(Unit) {
                        OnboardingPrefs.observe(this@MainActivity).collect { done ->
                            onboardingDone = done
                        }
                    }

                    val doneValue = onboardingDone
                    if (doneValue != null) {
                        PequeLeyNavGraph(
                            repository = app.repository,
                            hasCompletedOnboarding = doneValue,
                            onOnboardingDone = {
                                lifecycleScope.launch { OnboardingPrefs.setDone(this@MainActivity) }
                            }
                        )
                    }
                }
            }
        }
    }
}
