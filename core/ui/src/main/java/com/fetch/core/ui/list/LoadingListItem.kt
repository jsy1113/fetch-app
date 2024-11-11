package com.fetch.core.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LazyItemScope.LoadingListItem(modifier: Modifier = Modifier) {
    // randomizes width for loading items.
    val randomTitleWidth = remember {
        (Math.random() * 0.4f + 0.2f).toFloat()
    }

    val randomSubtitleWidth = remember {
        (Math.random() * 0.3f + 0.5f).toFloat()
    }

    ListItem(modifier = modifier,
        leadingContent = {
            Box(
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
            )
        },
        headlineContent = {
            Column {
                Box(
                    Modifier
                        .fillParentMaxWidth(randomTitleWidth)
                        .heightIn(14.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
                )
                Spacer(Modifier.height(8.dp))
            }
        },
        supportingContent = {
            Box(
                Modifier
                    .fillParentMaxWidth(randomSubtitleWidth)
                    .heightIn(14.dp)
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
            )
        })
}

@Preview
@Composable
private fun PreviewLoadingListItem() {
    LazyColumn {
        items(10) {
            LoadingListItem()
        }
    }
}
