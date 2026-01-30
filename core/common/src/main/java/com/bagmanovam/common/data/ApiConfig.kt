package com.bagmanovam.common.data

interface ApiConfig {
    val schema: String
    val host: String
    val port: Int?
    val apiKey: String
}