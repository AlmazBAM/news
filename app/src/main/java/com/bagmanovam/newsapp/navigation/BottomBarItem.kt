package com.bagmanovam.newsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.bagmanovam.navigation.AppRoute

sealed class BottomBarItem(
    val route: AppRoute,
    val label: String,
    val icon: ImageVector
) {
    object Home: BottomBarItem(AppRoute.Home, "Home", Icons.Outlined.Home)
    object Discover: BottomBarItem(AppRoute.Discover, "Discover", Icons.Outlined.Search)
    object Saved: BottomBarItem(AppRoute.Saved, "Saved", Icons.Outlined.Bookmark)
    object Setting: BottomBarItem(AppRoute.Settings, "Setting", Icons.Outlined.Settings)

    companion object {
        val items: List<BottomBarItem>
            get () = listOf(Home, Discover, Saved, Setting)
    }
}