package com.fetch.data.list.di

import com.fetch.data.list.service.ItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesListService(retrofit: Retrofit): ItemService =
        retrofit.create(ItemService::class.java)
}