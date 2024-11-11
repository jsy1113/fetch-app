package com.fetch.feature.list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fetch.core.ui.list.FetchListItem
import com.fetch.feature.list.model.ListItemState
import kotlinx.collections.immutable.ImmutableMap

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeList(
    modifier: Modifier = Modifier,
    listUiStateMap: ImmutableMap<Int, List<ListItemState>>,
) {
    LazyColumn(modifier = modifier) {
        listUiStateMap.forEach { (initial, contactsForInitial) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    text = initial.toString(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            items(contactsForInitial, key = { it.id }) {
                FetchListItem(
                    titleText = it.name,
                    subtitleText = it.listId.toString(),
                )
            }
        }
    }
}
