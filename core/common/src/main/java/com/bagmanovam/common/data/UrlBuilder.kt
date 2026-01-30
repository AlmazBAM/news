package com.bagmanovam.common.data

interface UrlBuilder {
    fun build(endpoint: Endpoint): String
}