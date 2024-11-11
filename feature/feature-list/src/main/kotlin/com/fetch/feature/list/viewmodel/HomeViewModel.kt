package com.fetch.feature.list.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.data.list.repository.ItemRepository
import com.fetch.feature.list.model.ListItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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

    private val _listUiState = MutableStateFlow<ImmutableList<ListItemState>>(
        persistentListOf()
    )

    val listUiState: StateFlow<ImmutableList<ListItemState>> =
        _listUiState.onStart {
            fetchListItem()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), persistentListOf())

    fun fetchListItem() {
        viewModelScope.launch {
            _screenState.tryEmit(HomeScreenUiState.Loading)

            val response = repository.getFetchList()
            val listItemMap = response.getOrDefault(emptyMap()).flatMap { (key, items) ->
                listOf(ListItemState.Header(key)) + items.map {
                    ListItemState.Item(it.id, it.listId, it.name)
                }
            }
            _listUiState.tryEmit(listItemMap.toImmutableList())

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