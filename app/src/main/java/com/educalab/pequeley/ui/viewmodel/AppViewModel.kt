package com.educalab.pequeley.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educalab.pequeley.data.repository.PequeLeyRepository
import com.educalab.pequeley.domain.engine.ProgressEngine
import com.educalab.pequeley.domain.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AppUiState(
    val loading: Boolean = true,
    val profile: UserProfileModel? = null,
    val rooms: List<HouseRoom> = emptyList(),
    val roomProgress: Map<String, RoomProgress> = emptyMap(),
    val garden: GardenState = GardenState(),
    val badges: List<BadgeModel> = emptyList(),
    val houseProgress: Float = 0f,
    val newlyUnlockedRoom: HouseRoom? = null,
    val newBadgeEarned: BadgeModel? = null
)

class AppViewModel(private val repository: PequeLeyRepository) : ViewModel() {

    private val progressEngine = ProgressEngine()

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        bootstrap()
    }

    private fun bootstrap() {
        viewModelScope.launch {
            val profile = repository.getOrCreateProfile()
            refreshAll(profile.id)
        }
    }

    fun refreshAll(userId: Long) {
        viewModelScope.launch {
            val rooms = repository.getRooms(userId)
            val progresses = repository.allRoomProgress(userId).associateBy { it.roomCode }
            val garden = repository.observeGarden(userId)
            val badges = repository.getBadges(userId)
            val houseProgress = repository.overallHouseProgress(userId)
            _uiState.value = _uiState.value.copy(
                loading = false,
                profile = repository.getOrCreateProfile(),
                rooms = rooms,
                roomProgress = progresses,
                badges = badges,
                houseProgress = houseProgress
            )
        }
        viewModelScope.launch {
            repository.observeGarden(userId).collect { garden ->
                _uiState.value = _uiState.value.copy(garden = garden)
            }
        }
    }

    fun createProfile(alias: String, avatarId: Int, onDone: () -> Unit) {
        viewModelScope.launch {
            val profile = repository.createProfile(alias, avatarId)
            refreshAll(profile.id)
            onDone()
        }
    }

    fun stateForRoom(room: HouseRoom): RoomModuleState =
        progressEngine.stateFor(room, _uiState.value.roomProgress[room.code])

    fun consumeUnlockNotice() {
        _uiState.value = _uiState.value.copy(newlyUnlockedRoom = null)
    }

    fun consumeBadgeNotice() {
        _uiState.value = _uiState.value.copy(newBadgeEarned = null)
    }

    fun onNewBadges(badges: List<BadgeModel>) {
        val userId = _uiState.value.profile?.id ?: return
        if (badges.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(newBadgeEarned = badges.first())
        }
        refreshAll(userId)
    }

    fun setSoundEnabled(enabled: Boolean) {
        val userId = _uiState.value.profile?.id ?: return
        viewModelScope.launch {
            repository.setSoundEnabled(userId, enabled)
            refreshAll(userId)
        }
    }

    fun setHapticEnabled(enabled: Boolean) {
        val userId = _uiState.value.profile?.id ?: return
        viewModelScope.launch {
            repository.setHapticEnabled(userId, enabled)
            refreshAll(userId)
        }
    }
}

class AppViewModelFactory(private val repository: PequeLeyRepository) : androidx.lifecycle.ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(repository) as T
    }
}
