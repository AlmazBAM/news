package com.bagmanovam.newsapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bagmanovam.navigation.AppRoute
import com.bagmanovam.news.navigation.homeGraph
import com.bagmanovam.setting.settingGraph


@Composable
fun AppNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = AppRoute.Home
    ) {
        settingGraph(navHostController)
        homeGraph(navHostController)

        // TODO: Убрать экраны по умолчанию. Временное решение
        composable<AppRoute.Discover> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Discover")
            }
        }
        composable<AppRoute.Saved> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Saved")
            }
        }
    }
}