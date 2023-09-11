package com.app.demo.core.remote.retrofit

import com.app.demo.core.network.BuildConfig
import com.app.demo.core.remote.RemoteDataSource
import com.app.demo.core.remote.model.RemoteArticles
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitRemoteApi{
    @GET("svc/topstories/v2/world.json")
    suspend fun getTopStoriesByWorld(@Query(value = "api-key") apiKey: String): RemoteArticles
}

class RemoteDataSourceImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : RemoteDataSource {

    private val remoteApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BACKEND_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitRemoteApi::class.java)


    override suspend fun getTopStoriesByWorld(): RemoteArticles {
        return remoteApi.getTopStoriesByWorld(BuildConfig.API_KEY)
    }

}