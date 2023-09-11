package com.app.demo.core.remote.di

import com.app.demo.core.remote.RemoteDataSource
import com.app.demo.core.remote.retrofit.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {
    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):RemoteDataSource
}