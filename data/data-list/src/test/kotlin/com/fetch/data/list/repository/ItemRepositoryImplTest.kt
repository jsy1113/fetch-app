package com.fetch.data.list.repository

import com.fetch.data.list.fakes.fakeItemList
import com.fetch.data.list.service.ItemService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase.fail
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ItemRepositoryImplTest {
    @MockK
    private lateinit var itemService: ItemService
    private lateinit var repository: ItemRepository
    private val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ItemRepositoryImpl(itemService, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }

    @Test
    fun `check name isNotBlank`() = runTest(testDispatcher) {
        coEvery { itemService.getListItem() } returns Response.success(fakeItemList)
        val response = repository.getFetchList()
        val body = response.getOrNull() ?: emptyMap()

        print(response)

        body.values.flatten().forEach {
            assert(it.name.isNotBlank())
        }
    }

    @Test
    fun `check listId isSorted`() = runTest(testDispatcher) {
        coEvery { itemService.getListItem() } returns Response.success(fakeItemList)
        val response = repository.getFetchList()
        val body = response.getOrNull() ?: emptyMap()

        var tempId = -1
        body.keys.forEach {
            if (it < tempId) {
                fail()
            }
            tempId = it
        }
    }

    @Test
    fun `check name isSorted`() = runTest(testDispatcher) {
        coEvery { itemService.getListItem() } returns Response.success(fakeItemList)
        val response = repository.getFetchList()
        val body = response.getOrNull() ?: emptyMap()

        body.forEach { (key, items) ->
            var tempName = ""
            items.forEach {
                println("${it.name} $tempName")
                if (it.name < tempName) {
                    fail()
                }
                tempName = it.name
            }
        }
    }

    @Test
    fun `check group hasCorrectChild`() = runTest(testDispatcher) {
        coEvery { itemService.getListItem() } returns Response.success(fakeItemList)
        val response = repository.getFetchList()
        val body = response.getOrNull() ?: emptyMap()

        body.forEach { (key, items) ->
            items.forEach {
                if (it.listId != key) {
                    fail()
                }
            }
        }
    }
}