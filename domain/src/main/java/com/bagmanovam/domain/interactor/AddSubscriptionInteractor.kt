package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.AddSubscriptionUseCase

class AddSubscriptionInteractor(
    private val newsRepository: NewsRepository
) : AddSubscriptionUseCase {
    override suspend fun invoke(topic: String) {
        newsRepository.addSubscription(topic)
        newsRepository.updateArticlesForTopic(topic)
    }
}