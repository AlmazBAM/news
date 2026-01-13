package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.ClearAllArticlesUseCase

class ClearAllArticlesInteractor(
    private val newsRepository: NewsRepository
) : ClearAllArticlesUseCase {
    override suspend fun invoke(topics: List<String>) {
        newsRepository.clearAllArticles(topics)
    }
}