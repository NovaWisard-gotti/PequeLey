package com.educalab.pequeley.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.educalab.pequeley.data.repository.PequeLeyRepository
import com.educalab.pequeley.ui.screens.*
import com.educalab.pequeley.ui.viewmodel.*

private object Routes {
    const val ONBOARDING = "onboarding"
    const val PROFILE_SETUP = "profile_setup"
    const val HOUSE = "house"
    const val ROOM = "room/{roomCode}"
    const val SITUATION = "situation/{roomCode}/{situationCode}"
    const val STORY = "story/{roomCode}/{storyCode}"
    const val BADGES = "badges"
    const val SETTINGS = "settings"

    fun room(code: String) = "room/$code"
    fun situation(roomCode: String, code: String) = "situation/$roomCode/$code"
    fun story(roomCode: String, code: String) = "story/$roomCode/$code"
}

@Composable
fun PequeLeyNavGraph(repository: PequeLeyRepository, hasCompletedOnboarding: Boolean, onOnboardingDone: () -> Unit) {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel(factory = AppViewModelFactory(repository))

    NavHost(navController = navController, startDestination = if (hasCompletedOnboarding) Routes.HOUSE else Routes.ONBOARDING) {

        composable(Routes.ONBOARDING) {
            OnboardingScreen(onFinished = {
                onOnboardingDone()
                navController.navigate(Routes.PROFILE_SETUP) { popUpTo(Routes.ONBOARDING) { inclusive = true } }
            })
        }

        composable(Routes.PROFILE_SETUP) {
            ProfileSetupScreen(onConfirm = { alias, avatarId ->
                appViewModel.createProfile(alias, avatarId) {
                    navController.navigate(Routes.HOUSE) { popUpTo(Routes.PROFILE_SETUP) { inclusive = true } }
                }
            })
        }

        composable(Routes.HOUSE) {
            HouseScreen(
                viewModel = appViewModel,
                onOpenRoom = { code -> navController.navigate(Routes.room(code)) },
                onOpenBadges = { navController.navigate(Routes.BADGES) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(
            Routes.ROOM,
            arguments = listOf(navArgument("roomCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val roomCode = backStackEntry.arguments?.getString("roomCode") ?: return@composable
            val userId = appViewModel.uiState.collectAsState().value.profile?.id ?: return@composable
            val roomViewModel: RoomDetailViewModel = viewModel(
                key = "room_$roomCode",
                factory = RoomDetailViewModelFactory(repository, roomCode, userId)
            )
            RoomDetailScreen(
                viewModel = roomViewModel,
                onBack = { navController.popBackStack() },
                onOpenSituation = { code -> navController.navigate(Routes.situation(roomCode, code)) },
                onOpenStory = { code -> navController.navigate(Routes.story(roomCode, code)) }
            )
        }

        composable(
            Routes.SITUATION,
            arguments = listOf(
                navArgument("roomCode") { type = NavType.StringType },
                navArgument("situationCode") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val situationCode = backStackEntry.arguments?.getString("situationCode") ?: return@composable
            val userId = appViewModel.uiState.collectAsState().value.profile?.id ?: return@composable
            val situationViewModel: SituationPlayViewModel = viewModel(
                key = "sit_$situationCode",
                factory = SituationPlayViewModelFactory(repository, situationCode, userId)
            )
            SituationPlayScreen(
                viewModel = situationViewModel,
                onBack = { navController.popBackStack() },
                onFinished = {
                    appViewModel.refreshAll(userId)
                    navController.popBackStack()
                }
            )
        }

        composable(
            Routes.STORY,
            arguments = listOf(
                navArgument("roomCode") { type = NavType.StringType },
                navArgument("storyCode") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val roomCode = backStackEntry.arguments?.getString("roomCode") ?: return@composable
            val storyCode = backStackEntry.arguments?.getString("storyCode") ?: return@composable
            val userId = appViewModel.uiState.collectAsState().value.profile?.id ?: return@composable
            val storyViewModel: StoryPlayViewModel = viewModel(
                key = "story_$storyCode",
                factory = StoryPlayViewModelFactory(repository, storyCode, roomCode, userId)
            )
            StoryPlayScreen(
                viewModel = storyViewModel,
                onBack = { navController.popBackStack() },
                onFinished = {
                    appViewModel.refreshAll(userId)
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.BADGES) {
            BadgesScreen(viewModel = appViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(viewModel = appViewModel, onBack = { navController.popBackStack() })
        }
    }
}
