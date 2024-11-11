package com.fetch.data.list.service

import com.fetch.data.list.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET


interface  ItemService {

    @GET("hiring.json")
    suspend fun getListItem(): Response<List<ItemResponse>>
}