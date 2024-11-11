package com.fetch.data.list.vo

import com.fetch.core.common.model.Vo
import com.fetch.data.list.model.ItemResponse

data class ItemVo(
    val id: Int,
    val listId: Int,
    val name: String,
) : Vo

fun ItemResponse.toVo(): ItemVo = ItemVo(id, listId, name)