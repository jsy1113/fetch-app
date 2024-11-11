package com.fetch.data.list.fakes

import com.fetch.data.list.model.ItemResponse


internal val fakeItemList = listOf(
    ItemResponse(id = 1, listId = 1, name = "name"),
    ItemResponse(id = 2, listId = 2, name = ""),
    ItemResponse(id = 3, listId = 4, name = "name2"),
    ItemResponse(id = 4, listId = 2, name = "name3"),
    ItemResponse(id = 4, listId = 2, name = "name4"),
    ItemResponse(id = 5, listId = 2, name = null),
    ItemResponse(id = 6, listId = 4, name = ""),
)