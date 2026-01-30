package com.bagmanovam.newsapp.di

import com.bagmanovam.common.data.ApiConfig
import com.bagmanovam.newsapp.BuildConfig
import org.koin.dsl.module

val platformModule = module {
    single<ApiConfig> {
        object : ApiConfig {
            override val schema: String
                get() = "https"
            override val host: String
                get() = "newsapi.org"
            override val port: Int?
                get() = null
            override val apiKey: String
                get() = BuildConfig.NEWS_API_KEY
        }
    }
}
