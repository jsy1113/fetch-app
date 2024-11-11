package com.fetch.data.list.di

import com.fetch.data.list.repository.ItemRepository
import com.fetch.data.list.repository.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindsListRepository(itemRepositoryImpl: ItemRepositoryImpl): ItemRepository

}