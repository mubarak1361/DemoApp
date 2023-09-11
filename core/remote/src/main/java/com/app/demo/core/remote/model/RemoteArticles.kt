package com.app.demo.core.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteArticles(
    val copyright: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("num_results")
    val numResults: Int,
    val results: List<RemoteArticle>,
    val section: String,
    val status: String
)