package com.fetch.data.list.model

import com.fetch.core.common.model.Entity
import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val id: Int,
    val listId: Int,
    val name: String?,
) : Entity
