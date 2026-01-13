package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.RemoveSubscriptionUseCase

class RemoveSubscriptionInteractor(
    private val newsRepository: NewsRepository
) : RemoveSubscriptionUseCase {
    override suspend fun invoke(topic: String) {
        newsRepository.removeSubscription(topic)
    }
}