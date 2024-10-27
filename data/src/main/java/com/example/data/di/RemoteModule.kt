package com.example.data.di

import com.example.data.remote.api.PokeApi
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideApi(): PokeApi {
        return Retrofit.Builder()
            .baseUrl(POKE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
            .create(PokeApi::class.java)
    }

    companion object {
        const val POKE_URL = "https://pokeapi.co/api/v2/"
    }

}


