package com.educalab.pequeley.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educalab.pequeley.data.repository.PequeLeyRepository
import com.educalab.pequeley.domain.engine.StorySession
import com.educalab.pequeley.domain.model.BadgeModel
import com.educalab.pequeley.domain.model.StoryChoiceModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StoryPlayState(
    val loading: Boolean = true,
    val session: StorySession? = null,
    val saved: Boolean = false,
    val newBadges: List<BadgeModel> = emptyList()
)

class StoryPlayViewModel(
    private val repository: PequeLeyRepository,
    private val storyCode: String,
    private val roomCode: String,
    private val userId: Long
) : ViewModel() {

    private val _state = MutableStateFlow(StoryPlayState())
    val state: StateFlow<StoryPlayState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val story = repository.getStory(storyCode)
            if (story != null) {
                val session = repository.startStory(story)
                _state.value = StoryPlayState(loading = false, session = session)
            }
        }
    }

    fun choose(choice: StoryChoiceModel) {
        val session = _state.value.session ?: return
        val updated = repository.chooseStoryOption(session, choice)
        _state.value = _state.value.copy(session = updated)
        if (updated.finished) persist()
    }

    private fun persist() {
        viewModelScope.launch {
            val newBadges = repository.completeStory(userId, roomCode, storyCode)
            _state.value = _state.value.copy(saved = true, newBadges = newBadges)
        }
    }
}

class StoryPlayViewModelFactory(
    private val repository: PequeLeyRepository,
    private val storyCode: String,
    private val roomCode: String,
    private val userId: Long
) : androidx.lifecycle.ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return StoryPlayViewModel(repository, storyCode, roomCode, userId) as T
    }
}
