package com.educalab.pequeley.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PequeLeyColorScheme = lightColorScheme(
    primary = PequeOrange,
    onPrimary = PequeCream,
    primaryContainer = PequeYellow,
    secondary = PequeBlue,
    onSecondary = PequeCream,
    tertiary = PequeLila,
    background = PequeCream,
    onBackground = PequeInk,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = PequeInk,
    error = androidx.compose.ui.graphics.Color(0xFFD64545)
)

@Composable
fun PequeLeyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PequeLeyColorScheme,
        typography = PequeLeyTypography,
        content = content
    )
}
