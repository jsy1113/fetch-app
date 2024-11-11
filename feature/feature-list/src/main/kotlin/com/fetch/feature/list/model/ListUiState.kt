package com.fetch.feature.list.model

import androidx.compose.runtime.Immutable


sealed interface ListItemState {

    @Immutable
    data class Header(private val listId: Int) : ListItemState

    @Immutable
    data class Item(
        val id: Int,
        val listId: Int,
        val name: String,
    ) : ListItemState

}
