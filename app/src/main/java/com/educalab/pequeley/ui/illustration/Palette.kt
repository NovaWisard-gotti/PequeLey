package com.educalab.pequeley.ui.illustration

import androidx.compose.ui.graphics.Color

/**
 * Genera paletas de color cálidas y coherentes a partir de una semilla
 * entera. Esto permite que cada personaje/objeto/insignia tenga una
 * apariencia distinta y reproducible sin depender de assets bitmap
 * externos (ver sección "Ilustraciones e imágenes" de la especificación).
 */
data class Palette(val base: Color, val accent: Color, val light: Color, val dark: Color, val skin: Color)

object PaletteFactory {

    // Paleta curada de tonos cálidos/aventureros (evita estética "bebé").
    private val baseHues = listOf(
        0xFFF6A93B, // naranja cálido
        0xFF5B8DEF, // azul aventura
        0xFFE2725B, // terracota
        0xFF8FD4C1, // verde agua
        0xFFF2C14E, // amarillo miel
        0xFFB185DB, // lila
        0xFF6FCF97, // verde jardín
        0xFFEF9CC2, // rosa suave
        0xFF4FB8A8, // turquesa
        0xFFE07A5F, // coral
        0xFF9B8CF2, // violeta
        0xFFF4A261  // ámbar
    ).map { Color(it) }

    private val skinTones = listOf(
        0xFFF6D2B5, 0xFFE8B287, 0xFFC98A5A, 0xFF8D5A3C, 0xFFF3E0C8
    ).map { Color(it) }

    fun forSeed(seed: Int): Palette {
        val s = if (seed == 0) 1 else kotlin.math.abs(seed)
        val base = baseHues[s % baseHues.size]
        val accent = baseHues[(s / 3 + 1) % baseHues.size]
        val skin = skinTones[s % skinTones.size]
        return Palette(
            base = base,
            accent = accent,
            light = lighten(base, 0.35f),
            dark = darken(base, 0.25f),
            skin = skin
        )
    }

    private fun lighten(color: Color, amount: Float): Color = Color(
        red = (color.red + (1f - color.red) * amount).coerceIn(0f, 1f),
        green = (color.green + (1f - color.green) * amount).coerceIn(0f, 1f),
        blue = (color.blue + (1f - color.blue) * amount).coerceIn(0f, 1f),
        alpha = 1f
    )

    private fun darken(color: Color, amount: Float): Color = Color(
        red = (color.red * (1f - amount)).coerceIn(0f, 1f),
        green = (color.green * (1f - amount)).coerceIn(0f, 1f),
        blue = (color.blue * (1f - amount)).coerceIn(0f, 1f),
        alpha = 1f
    )
}
