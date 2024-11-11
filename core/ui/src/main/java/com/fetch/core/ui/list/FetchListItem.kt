package com.fetch.core.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LazyItemScope.FetchListItem(
    modifier: Modifier = Modifier,
    titleText: String,
    subtitleText: String,
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
        },
        headlineContent = {
            Text(titleText, style = MaterialTheme.typography.titleSmall)
        },
        supportingContent = {
            Text(subtitleText, style = MaterialTheme.typography.bodyMedium)
        }
    )
}

@Preview
@Composable
private fun PreviewFetchListItem() {
    LazyColumn {
        items(10) { index: Int ->
            FetchListItem(
                titleText = "title Text $index",
                subtitleText = "subTitle Text $index",
            )
        }
    }
}