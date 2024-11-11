package com.fetch.feature.list.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.data.list.repository.ItemRepository
import com.fetch.feature.list.model.ListItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ItemRepository,
) : ViewModel() {
    private val _screenState =
        MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val screenUiState = _screenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeScreenUiState.Loading
    )

    private val _listUiState = MutableStateFlow<ImmutableMap<Int, List<ListItemState>>>(
        persistentMapOf()
    )

    val listUiState: StateFlow<ImmutableMap<Int, List<ListItemState>>> =
        _listUiState.onStart {
            fetchListItem()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), persistentMapOf())

    fun fetchListItem() {
        viewModelScope.launch {
            _screenState.tryEmit(HomeScreenUiState.Loading)

            val response = repository.getFetchList()
            val itemMap = response.getOrDefault(emptyMap())
            val listItemMap = itemMap.mapValues { value ->
                value.value.map { ListItemState(it.id, it.listId, it.name) }.toImmutableList()
            }.toImmutableMap()

            _listUiState.tryEmit(listItemMap)

            val newScreenState =
                if (response.isSuccess) (HomeScreenUiState.Idle)
                else (HomeScreenUiState.Error(response.exceptionOrNull()?.message.orEmpty()))
            _screenState.tryEmit(newScreenState)
        }
    }
}

@Stable
sealed interface HomeScreenUiState {
    data object Loading : HomeScreenUiState
    data object Idle : HomeScreenUiState
    data class Error(val errorMessage: String) : HomeScreenUiState
}