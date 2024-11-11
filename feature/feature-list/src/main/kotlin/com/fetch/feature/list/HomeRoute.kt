package com.fetch.feature.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fetch.feature.list.state.rememberHomeScreenState


@Composable
fun HomeRoute(/* Highest level composable, no modifier needed */) {
    val homeScreenState = rememberHomeScreenState()
    HomeScreen(
        modifier = Modifier,
        homeScreenUiState = homeScreenState.homeScreenUiState,
        homeScreenAction = homeScreenState.homeScreenAction,
        listUiStateMap = homeScreenState.listUiStateMap,
    )
}