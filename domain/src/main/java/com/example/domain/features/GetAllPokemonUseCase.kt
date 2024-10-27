package com.example.domain.features

import com.example.domain.model.PokemonItem
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): Flow<List<PokemonItem>> {
        return pokemonRepository.getAllPokemon()
    }
}