package com.nazirov.androidimperative.di

import com.nazirov.androidimperative.model.TVShowDetails
import com.nazirov.androidimperative.networking.Server.IS_TESTER
import com.nazirov.androidimperative.networking.Server.SERVER_DEVELOPMENT
import com.nazirov.androidimperative.networking.Server.SERVER_PRODUCTION
import com.nazirov.androidimperative.networking.TVShovService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /**
     * Retrofit Related
     */

    @Provides
    fun server(): String {
        if (IS_TESTER){
            return SERVER_DEVELOPMENT
        } else return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient():Retrofit{
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService():TVShovService{
        return retrofitClient().create(TVShovService::class.java)
    }

    @Provides
    @Singleton
    fun tvShowSDetails():TVShowDetails{
        return retrofitClient().create(TVShowDetails::class.java)
    }






    /**
     * Room Related
     */
}