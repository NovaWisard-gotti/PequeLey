package com.educalab.pequeley.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educalab.pequeley.data.repository.PequeLeyRepository
import com.educalab.pequeley.domain.engine.SituationSession
import com.educalab.pequeley.domain.model.BadgeModel
import com.educalab.pequeley.domain.model.DecisionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SituationPlayState(
    val loading: Boolean = true,
    val session: SituationSession? = null,
    val lastConsequenceText: String? = null,
    val lastConsequencePositive: Boolean? = null,
    val saved: Boolean = false,
    val newBadges: List<BadgeModel> = emptyList()
)

class SituationPlayViewModel(
    private val repository: PequeLeyRepository,
    private val situationCode: String,
    private val userId: Long
) : ViewModel() {

    private val _state = MutableStateFlow(SituationPlayState())
    val state: StateFlow<SituationPlayState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val situation = repository.getSituation(situationCode)
            if (situation != null) {
                val session = repository.startSituation(situation)
                _state.value = SituationPlayState(loading = false, session = session)
            }
        }
    }

    fun advance() {
        val session = _state.value.session ?: return
        _state.value = _state.value.copy(session = repository.advanceSituation(session))
    }

    fun choose(decision: DecisionModel) {
        val session = _state.value.session ?: return
        val updated = repository.chooseSituationDecision(session, decision)
        val lastConsequence = updated.consequencesShown.lastOrNull()
        val consequenceText = lastConsequence?.let { repository.reflectionFor(it) }
        _state.value = _state.value.copy(
            session = updated,
            lastConsequenceText = consequenceText,
            lastConsequencePositive = lastConsequence?.isPositive
        )
        if (updated.finished) persist(updated)
    }

    private fun persist(session: SituationSession) {
        viewModelScope.launch {
            val newBadges = repository.completeSituation(userId, session)
            _state.value = _state.value.copy(saved = true, newBadges = newBadges)
        }
    }
}

class SituationPlayViewModelFactory(
    private val repository: PequeLeyRepository,
    private val situationCode: String,
    private val userId: Long
) : androidx.lifecycle.ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return SituationPlayViewModel(repository, situationCode, userId) as T
    }
}
