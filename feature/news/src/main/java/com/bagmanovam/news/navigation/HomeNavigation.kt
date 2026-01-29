package com.bagmanovam.news.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bagmanovam.navigation.AppRoute
import com.bagmanovam.news.HomeScreen
import com.bagmanovam.news.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(navHostController: NavHostController) {
    composable<AppRoute.Home> {
        val homeScreenViewModel = koinViewModel<HomeScreenViewModel>()
        val state by homeScreenViewModel.state.collectAsStateWithLifecycle()
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            state = state,
            onAction = homeScreenViewModel::onAction
        )
    }
}