package com.app.demo.core.remote.model


import kotlinx.serialization.Serializable

@Serializable
data class RemoteMultimedia(
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
)