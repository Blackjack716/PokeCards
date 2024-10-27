package com.example.data.remote.api

import com.example.data.remote.ErrorResponse
import com.example.data.remote.model.Pokemon
import com.example.data.remote.model.PokemonDto
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon?limit=100000&offset=0")
    suspend fun getAllPokemon(
        @Path("next", encoded = true) next : String? = null
    ): NetworkResponse<PokemonDto, ErrorResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): NetworkResponse<Pokemon, ErrorResponse>
}