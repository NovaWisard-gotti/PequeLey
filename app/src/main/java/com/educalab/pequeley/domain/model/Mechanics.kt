package com.educalab.pequeley.domain.model

/** Tipos de mecánica educativa. Nunca "solo opción múltiple": ver MECÁNICAS EDUCATIVAS. */
enum class MechanicType {
    OBSERVE_AND_ACT,
    ORDER_STEPS,
    DRAG_MATCH,
    BUILD_SOLUTION,
    DIALOGUE_CHOICE,
    CARE_OBJECT,
    COMPARE_CONSEQUENCES,
    SORT_FACTS,
    DECORATE,
    REFLECTION
}

enum class StepType {
    NARRATION,
    OBSERVATION,
    INTERACTION,
    DECISION,
    CONSEQUENCE,
    REFLECTION
}

enum class Mood {
    NEUTRAL, HAPPY, THINKING, SURPRISED, PROUD, CALM
}
