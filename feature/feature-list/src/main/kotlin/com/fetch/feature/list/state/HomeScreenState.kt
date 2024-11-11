package com.fetch.feature.list.state


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fetch.feature.list.model.ListItemState
import com.fetch.feature.list.viewmodel.HomeScreenUiState
import com.fetch.feature.list.viewmodel.HomeViewModel
import kotlinx.collections.immutable.ImmutableMap

@Stable
data class HomeScreenState(
    val homeScreenUiState: HomeScreenUiState,
    val listUiStateMap: ImmutableMap<Int, List<ListItemState>>,
    val homeScreenAction: (HomeScreenAction) -> Unit,
)

sealed interface HomeScreenAction {
    data object Refresh : HomeScreenAction
}

@Composable
fun rememberHomeScreenState(viewModel: HomeViewModel = hiltViewModel()): HomeScreenState {
    val homeScreenUiState by viewModel.screenUiState.collectAsStateWithLifecycle()
    val listUiState by viewModel.listUiState.collectAsStateWithLifecycle()

    // No need to remember lambda
    // Kotlin 2.0.20 has strong skipping on by default
    val onAction: (HomeScreenAction) -> Unit = {
        when (it) {
            is HomeScreenAction.Refresh -> {
                viewModel.fetchListItem()
            }
        }
    }

    return remember(homeScreenUiState, listUiState, onAction) {
        HomeScreenState(
            homeScreenUiState = homeScreenUiState,
            listUiStateMap = listUiState,
            homeScreenAction = onAction
        )
    }
}

