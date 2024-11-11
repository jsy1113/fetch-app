package com.fetch.data.list.repository

import com.fetch.core.common.annotations.IoDispatcher
import com.fetch.data.list.service.ItemService
import com.fetch.data.list.vo.ItemVo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ItemRepository {

    suspend fun getFetchList(): Result<Map<Int, List<ItemVo>>>
}

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ItemRepository {

    /**
     * requirement:
     * Display all the items grouped by "listId"
     * Sort the results first by "listId" then by "name" when displaying.
     * Filter out any items where "name" is blank or null.
     *
     *
     * Filtering out first to improve performance
     * Sort the item first, to improve performance and meet the requirements easier
     * Group last.
     * **/
    override suspend fun getFetchList(): Result<Map<Int, List<ItemVo>>> {
        return withContext(ioDispatcher) {
            val response = itemService.getListItem()
            if (response.isSuccessful) {
                val listItemBody = response.body() ?: emptyList()
                val itemVo = if (listItemBody.isNotEmpty()) {
                    val filteredItem = listItemBody.filterNot { it.name.isNullOrBlank() }
                    val sortedItem = filteredItem.sortedWith(compareBy({ it.listId }, { it.name }))
                    val itemVoList = sortedItem.map { ItemVo(it.id, it.listId, it.name ?: "") }
                    itemVoList.groupBy { it.listId }
                } else emptyMap()

                Result.success(itemVo)
            } else {
                Result.failure(RuntimeException("Empty List"))
            }
        }
    }

}
