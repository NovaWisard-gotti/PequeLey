# PequeLey — La Casa de las Buenas Decisiones

Aplicación educativa Android (nativa, Kotlin + Jetpack Compose) para
enseñar a niños de aproximadamente 8 años nociones básicas de
convivencia, derechos, responsabilidades, acuerdos y justicia
cotidiana — mediante una gran casa interactiva llena de historias,
no mediante un curso de Derecho.

- **Package**: `com.educalab.pequeley`
- **Versión**: 1.0.0
- **Idioma**: Español
- **Conectividad**: 100% offline (sin permiso `INTERNET`, sin backend, sin analítica)

## Por qué una casa y no un dashboard

PequeLey rechaza deliberadamente el patrón "menú de temas + Cards +
botones". La navegación principal es una casa ilustrada con 8
habitaciones (`ui/screens/HouseScreen.kt`), cada una con una escena
visual propia (`ui/illustration/RoomIllustration.kt`) y mecánicas de
aprendizaje distintas: observar, decidir, construir acuerdos,
comparar consecuencias, cuidar objetos, vivir historias ramificadas.

## Estructura del repositorio

```
app/                    Código fuente Android (Kotlin + Compose)
  src/main/java/...     data/ (Room) · domain/ (motores puros) · ui/ (Compose)
  src/test/java/...     ~68 tests JUnit del dominio
database/
  schema.sql            Esquema SQL completo (25 tablas)
  sample_data.sql        Muestra representativa de datos semilla en SQL
docs/
  MEMORIA_DESCRIPTIVA.md  Qué es la app, para quién, y cómo se diseñó
  MANUAL_USUARIO.md       Cómo se usa (para familias/docentes)
  MANUAL_TECNICO.md       Arquitectura, decisiones técnicas
  BASE_DE_DATOS.md        Modelo de datos con DER
  BUILD_REPORT.md         Estado real de compilación y pruebas
  pdf/                    Versiones PDF de los tres manuales anteriores
.github/workflows/
  build.yml              CI que compila el APK real en GitHub Actions
deliverables/            Entregables finales (APK, ZIP, PDFs)
```

## Cómo compilar

Este proyecto se generó en un entorno sandbox **sin acceso de red al
Android SDK ni a Google Maven** (ver `docs/BUILD_REPORT.md` para el
detalle exacto). Por eso la compilación de un APK real no pudo
verificarse localmente durante la generación. Para compilarlo:

```bash
git clone <tu-repositorio>
cd PequeLey
./gradlew clean
./gradlew testDebugUnitTest
./gradlew lintDebug
./gradlew assembleDebug
```

O simplemente haz `git push`: el workflow en
`.github/workflows/build.yml` compila el proyecto automáticamente en
GitHub Actions (que sí tiene acceso completo a internet) y publica el
APK de debug, el reporte de tests y el reporte de lint como artefactos
descargables de la ejecución.

## Verificación real ya realizada (sin Android SDK)

La capa de dominio (`domain/model` + `domain/engine`, sin ninguna
dependencia de Android) fue compilada y ejecutada de forma
independiente con `kotlinc` dentro de este entorno, como smoke test:
**27/27 aserciones pasaron** sobre los 6 motores de dominio
(`SituationEngine`, `ConsequenceEngine`, `AgreementEngine`,
`StoryEngine`, `ProgressEngine`, `RewardEngine`). El detalle está en
`docs/BUILD_REPORT.md`.

## Privacidad

No se solicitan datos personales reales. Solo un alias y un avatar
elegido entre ilustraciones locales. Toda la información se guarda
únicamente en el dispositivo mediante Room/SQLite.
