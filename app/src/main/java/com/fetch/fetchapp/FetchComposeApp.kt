package com.fetch.fetchapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fetch.feature.list.HomeRoute
import com.fetch.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchComposeApp() {
    val navController = rememberNavController()
    NavigationSuiteScaffold(
        modifier = Modifier,
        navigationSuiteItems = {
            // only 1 available, keep it selected
            item(selected = true, icon = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Rounded.Home,
                        contentDescription = stringResource(R.string.content_desc_home),
                    )
                    Text(
                        text = stringResource(R.string.home_bottom_tab_string),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }, onClick = {

            })
        }
    ) {
        Column {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                })
            NavHost(
                navController = navController,
                startDestination = TopLevelDestination.Home
            ) {
                composable<TopLevelDestination.Home> {
                    HomeRoute()
                }
            }
        }
    }
}
