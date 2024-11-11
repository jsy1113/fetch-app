package com.fetch.core.ui.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fetch.feature.list.R


@Composable
fun ErrorIconMessage(
    modifier: Modifier = Modifier,
    iconTint: Color,
    iconModifier: Modifier = Modifier,
    iconImageVector: ImageVector,
    noticeText: String,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = iconImageVector,
            contentDescription = stringResource(R.string.content_desc_error_icon),
            tint = iconTint
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            noticeText,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }

}

@Preview
@Composable
private fun PreviewNoticeIconMessage() {
    ErrorIconMessage(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer),
        iconImageVector = Icons.Default.Warning,
        iconModifier = Modifier.size(64.dp),
        noticeText = "Try again",
        iconTint = MaterialTheme.colorScheme.error,
    )
}