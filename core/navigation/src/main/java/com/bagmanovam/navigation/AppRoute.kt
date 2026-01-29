package com.bagmanovam.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object Home: AppRoute

    @Serializable
    data object Discover: AppRoute

    @Serializable
    data object Saved: AppRoute

    @Serializable
    data object Settings: AppRoute
}