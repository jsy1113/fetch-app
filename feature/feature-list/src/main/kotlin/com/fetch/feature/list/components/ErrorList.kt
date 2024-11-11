package com.fetch.feature.list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fetch.core.ui.error.ErrorIconMessage
import com.fetch.feature.list.R


@Composable
fun ErrorList(modifier: Modifier = Modifier, errorMessage: String) {
    val scrollState = rememberScrollState()

    ErrorIconMessage(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        iconTint = MaterialTheme.colorScheme.error,
        iconModifier = Modifier.size(64.dp),
        noticeText = stringResource(
            R.string.feature_list_error_message,
            errorMessage
        ),
        iconImageVector = Icons.TwoTone.Warning
    )
}