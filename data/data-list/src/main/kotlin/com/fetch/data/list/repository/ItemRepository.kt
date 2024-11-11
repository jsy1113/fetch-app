package com.fetch.data.list.repository

import com.fetch.core.common.annotations.IoDispatcher
import com.fetch.data.list.service.ItemService
import com.fetch.data.list.vo.ItemVo
import com.fetch.data.list.vo.toVo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ItemRepository {

    suspend fun getFetchList(): Result<List<ItemVo>>
}

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ItemRepository {

    override suspend fun getFetchList(): Result<List<ItemVo>> {
        return withContext(ioDispatcher) {
            val response = itemService.getListItem()
            if (response.isSuccessful) {
                val itemVo = response.body()?.map {
                    it.toVo()
                } ?: emptyList()
                Result.success(itemVo)
            } else {
                Result.failure(RuntimeException("Empty List"))
            }
        }
    }

}
