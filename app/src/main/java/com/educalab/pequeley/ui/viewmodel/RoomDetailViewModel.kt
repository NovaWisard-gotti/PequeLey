package com.educalab.pequeley.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educalab.pequeley.data.repository.PequeLeyRepository
import com.educalab.pequeley.domain.engine.AgreementEngine
import com.educalab.pequeley.domain.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RoomDetailState(
    val loading: Boolean = true,
    val room: HouseRoom? = null,
    val situations: List<Situation> = emptyList(),
    val stories: List<StoryModel> = emptyList(),
    val responsibilityTasks: List<ResponsibilityTaskModel> = emptyList(),
    val rightLessons: List<RightLessonModel> = emptyList(),
    val concepts: List<LegalConcept> = emptyList(),
    val challenges: List<ChallengeModel> = emptyList(),
    val agreements: List<AgreementModel> = emptyList(),
    val garden: GardenState = GardenState(),
    val agreementSymbols: List<AgreementSymbol> = AgreementSymbols.ALL,
    val agreementBuilderSelection: List<String> = emptyList(),
    val agreementBuildError: String? = null,
    val lastAgreementCreated: AgreementModel? = null
)

class RoomDetailViewModel(
    private val repository: PequeLeyRepository,
    private val roomCode: String,
    private val userId: Long
) : ViewModel() {

    private val agreementEngine = AgreementEngine()
    private val _state = MutableStateFlow(RoomDetailState())
    val state: StateFlow<RoomDetailState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val rooms = repository.getRooms(userId)
            val room = rooms.firstOrNull { it.code == roomCode }
            val situations = repository.getSituationsForRoom(roomCode)

            var stories = emptyList<StoryModel>()
            var responsibilityTasks = emptyList<ResponsibilityTaskModel>()
            var rightLessons = emptyList<RightLessonModel>()
            var concepts = emptyList<LegalConcept>()
            var agreements = emptyList<AgreementModel>()
            var challenges = emptyList<ChallengeModel>()

            when (roomCode) {
                "historias" -> stories = repository.getStories()
                "responsabilidades" -> responsibilityTasks = repository.getResponsibilityTasks()
                "derechos" -> {
                    rightLessons = repository.getRightLessons()
                    concepts = repository.getConcepts().filter { it.code in setOf("derecho", "respeto", "cuidado") }
                }
                "acuerdos" -> agreements = repository.getAgreements(userId)
                "convivencia" -> challenges = repository.getChallenges().filter { it.situationRef.startsWith("sit_") }
                "reglas" -> concepts = repository.getConcepts().filter { it.code in setOf("regla", "acuerdo") }
                "decisiones" -> concepts = repository.getConcepts().filter { it.code in setOf("consecuencia", "solucion") }
            }

            _state.value = _state.value.copy(
                loading = false, room = room, situations = situations, stories = stories,
                responsibilityTasks = responsibilityTasks, rightLessons = rightLessons,
                concepts = concepts, agreements = agreements, challenges = challenges
            )
        }
        viewModelScope.launch {
            repository.observeGarden(userId).collect { garden ->
                _state.value = _state.value.copy(garden = garden)
            }
        }
    }

    fun toggleSymbol(code: String) {
        val current = _state.value.agreementBuilderSelection
        val updated = if (current.contains(code)) current - code else current + code
        _state.value = _state.value.copy(agreementBuilderSelection = updated, agreementBuildError = null)
    }

    fun buildAgreement(title: String, situationCode: String? = null) {
        val selection = _state.value.agreementBuilderSelection
        viewModelScope.launch {
            try {
                val created = repository.createAgreement(userId, roomCode, title, situationCode, selection)
                _state.value = _state.value.copy(
                    lastAgreementCreated = created,
                    agreementBuilderSelection = emptyList(),
                    agreements = repository.getAgreements(userId)
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(agreementBuildError = e.message)
            }
        }
    }

    fun consumeNewAgreement() {
        _state.value = _state.value.copy(lastAgreementCreated = null)
    }
}

class RoomDetailViewModelFactory(
    private val repository: PequeLeyRepository,
    private val roomCode: String,
    private val userId: Long
) : androidx.lifecycle.ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return RoomDetailViewModel(repository, roomCode, userId) as T
    }
}
