// Root build file para PequeLey.
// Declara los plugins usados por los módulos pero no los aplica aquí (apply false),
// siguiendo el patrón estándar de Android Gradle Plugin + Kotlin DSL.
plugins {
    id("com.android.application") version "8.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
