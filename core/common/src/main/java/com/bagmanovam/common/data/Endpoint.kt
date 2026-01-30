package com.bagmanovam.common.data

interface Endpoint {
    val path: String
    val queryParams: Map<String, String?>
}