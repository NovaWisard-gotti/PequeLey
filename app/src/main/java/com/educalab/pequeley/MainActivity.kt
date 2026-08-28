package com.educalab.pequeley

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
        // PequeLey siempre usa un tema cálido claro (nunca oscuro), así que la
        // barra de estado/navegación debe mostrar íconos oscuros siempre,
        // sin importar el modo claro/oscuro del sistema del teléfono.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT)
        )

        val app = application as PequeLeyApplication

        setContent {
            PequeLeyTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                ) {
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
