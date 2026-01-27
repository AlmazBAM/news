package com.bagmanovam.news

sealed interface HomeScreenAction {
    data class InputTopic(val topic: String): HomeScreenAction
    data object OnClickSubscribe: HomeScreenAction

    data object OnRefreshData: HomeScreenAction

    data class OnToggleTopicSelection(val topic: String): HomeScreenAction

    data object OnClearArticles: HomeScreenAction

    data class RemoveSubscription(val topic: String): HomeScreenAction
}