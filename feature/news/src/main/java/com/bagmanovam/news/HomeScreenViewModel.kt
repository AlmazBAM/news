package com.bagmanovam.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.domain.usecase.AddSubscriptionUseCase
import com.bagmanovam.domain.usecase.ClearAllArticlesUseCase
import com.bagmanovam.domain.usecase.GetAllSubscriptionsUseCase
import com.bagmanovam.domain.usecase.GetArticlesByTopicsUseCase
import com.bagmanovam.domain.usecase.RemoveSubscriptionUseCase
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val addSubscriptionUseCase: AddSubscriptionUseCase,
    private val clearAllArticlesUseCase: ClearAllArticlesUseCase,
    private val getAllSubscriptionsUseCase: GetAllSubscriptionsUseCase,
    private val getArticleByTopicsUseCase: GetArticlesByTopicsUseCase,
    private val removeSubscriptionUseCase: RemoveSubscriptionUseCase,
    private val updateArticlesForAllSubscriptionsUseCase: UpdateArticlesForAllSubscriptionsUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.Loading)
    val state = _state
        .onStart {
            observeSelectedTopics()
            observeSubscriptions()
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = HomeScreenState.Loading
        )

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.InputTopic -> {
                val current = _state.value
                if (current is HomeScreenState.Success) {
                    _state.update { state ->
                        current.copy(
                            topic = action.topic
                        )
                    }
                }
            }

            HomeScreenAction.OnClearArticles -> {
                viewModelScope.launch {
                    val current = _state.value
                    if (current is HomeScreenState.Success) {
                        val topics = current.selectedTopics
                        clearAllArticlesUseCase(topics)
                    }
                }
            }

            HomeScreenAction.OnClickSubscribe -> {
                viewModelScope.launch {
                    val current = _state.value
                    if (current is HomeScreenState.Success) {
                        addSubscriptionUseCase(current.topic)
                        _state.update { state ->
                            current.copy(topic = "")
                        }
                    }
                }
            }

            HomeScreenAction.OnRefreshData -> {
                viewModelScope.launch {
                    updateArticlesForAllSubscriptionsUseCase()
                }
            }

            is HomeScreenAction.OnToggleTopicSelection -> {
                _state.update { state ->
                    if (state is HomeScreenState.Success) {
                        val subscriptions = state.subscriptions.toMutableMap()
                        val isSelected = subscriptions[action.topic] ?: false
                        subscriptions[action.topic] = !isSelected
                        state.copy(
                            subscriptions = subscriptions
                        )
                    } else {
                        state
                    }
                }
            }

            is HomeScreenAction.RemoveSubscription -> {
                viewModelScope.launch {
                    removeSubscriptionUseCase(action.topic)
                }
            }
        }
    }

    private fun observeSelectedTopics() {
        state.map { state ->
            if (state is HomeScreenState.Success) {
                state.selectedTopics
            } else {
                emptyList()
            }
        }
            .distinctUntilChanged()
            .flatMapLatest { topics ->
                getArticleByTopicsUseCase(topics)
            }
            .onEach {
                _state.update { state ->
                    if (state is HomeScreenState.Success) {
                        state.copy(
                            articles = it
                        )
                    } else {
                        state
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeSubscriptions() {
        getAllSubscriptionsUseCase()
            .onEach { subscriptions ->
                _state.update { prev ->
                    if (prev is HomeScreenState.Success) {
                        prev.copy(
                            subscriptions = subscriptions.associateWith { topic ->
                                prev.subscriptions[topic] ?: true
                            }
                        )
                    } else {
                        prev
                    }
                }
            }.launchIn(viewModelScope)
    }
}