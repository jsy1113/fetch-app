package com.fetch.feature.list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.fetch.core.ui.list.LoadingListItem


@Composable
fun LoadingList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.testTag("loading list")) {
        items(10) {
            LoadingListItem()
        }
    }
}