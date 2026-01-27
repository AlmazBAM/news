package com.bagmanovam.news

import com.bagmanovam.domain.model.Article

sealed interface HomeScreenState {
    data object Loading : HomeScreenState
    data class Success(
        val topic: String = "",
        val subscriptions: Map<String, Boolean> = mapOf(),
        val articles: List<Article> = listOf(),
    ) : HomeScreenState {
        val selectedTopics: List<String>
            get() = subscriptions.filter { it.value }.map { it.key }

        val subscribeButtonEnabled: Boolean
            get() = topic.isNotBlank()
    }
}