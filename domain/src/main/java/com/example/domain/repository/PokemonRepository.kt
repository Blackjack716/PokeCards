package com.example.domain.repository

import com.example.domain.model.PokemonItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemon(): Flow<List<PokemonItem>>
}