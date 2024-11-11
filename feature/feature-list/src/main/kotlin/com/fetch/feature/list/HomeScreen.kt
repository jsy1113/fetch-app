package com.fetch.feature.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.fetch.feature.list.components.EmptyList
import com.fetch.feature.list.components.ErrorList
import com.fetch.feature.list.components.HomeList
import com.fetch.feature.list.components.LoadingList
import com.fetch.feature.list.model.ListItemState
import com.fetch.feature.list.state.HomeScreenAction
import com.fetch.feature.list.viewmodel.HomeScreenUiState
import kotlinx.collections.immutable.ImmutableMap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenUiState: HomeScreenUiState,
    homeScreenAction: (HomeScreenAction) -> Unit,
    listUiStateMap: ImmutableMap<Int, List<ListItemState>>,
) {
    val isRefreshing = remember(homeScreenUiState) {
        homeScreenUiState is HomeScreenUiState.Loading
    }

    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        onRefresh = {
            homeScreenAction(HomeScreenAction.Refresh)
        }
    ) {
        when (homeScreenUiState) {
            is HomeScreenUiState.Error -> {
                ErrorList(modifier = Modifier, homeScreenUiState.errorMessage)
            }

            HomeScreenUiState.Loading -> {
                LoadingList(modifier = Modifier.fillMaxSize())
            }

            HomeScreenUiState.Idle -> {
                if (listUiStateMap.isEmpty()) {
                    EmptyList(modifier = Modifier)
                } else {
                    HomeList(
                        listUiStateMap = listUiStateMap,
                    )
                }
            }
        }
    }
}