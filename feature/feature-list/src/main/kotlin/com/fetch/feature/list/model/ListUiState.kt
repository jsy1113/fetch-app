package com.fetch.feature.list.model

import androidx.compose.runtime.Immutable

@Immutable
data class ListItemState(
    val id: Int,
    val listId: Int,
    val name: String,
)
