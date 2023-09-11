package com.app.demo.core.remote

import com.app.demo.core.remote.model.RemoteArticles

interface RemoteDataSource{
    suspend fun getTopStoriesByWorld(): RemoteArticles
}