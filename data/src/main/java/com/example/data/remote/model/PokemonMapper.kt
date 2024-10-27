package com.example.data.remote.model

import com.example.data.local.model.PokemonEntity

fun Pokemon.toEntity(): PokemonEntity {
    val pokemonTypesArray = arrayListOf<String>()
    val pokemonTypes = this.types.firstOrNull()?.types?.map { pokemonType -> pokemonType.name ?: "" } ?: emptyList()
    pokemonTypesArray.addAll(pokemonTypes)
    return PokemonEntity(
        id = this.id ?: 0,
        name = this.name ?: "",
        sprite = this.sprites.firstOrNull()?.sprite ?: "",
        typeList = pokemonTypesArray,
        isInCollection = false
    )
}