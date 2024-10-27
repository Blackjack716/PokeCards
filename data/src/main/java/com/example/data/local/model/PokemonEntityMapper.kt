package com.example.data.local.model

import com.example.domain.model.PokemonItem

fun PokemonItem.toEntity(): PokemonEntity {
    val pokemonTypesArray =  arrayListOf<String>()
    pokemonTypesArray.addAll(this.typeList)
    return PokemonEntity(
        id = this.id,
        name = this.name,
        sprite = this.sprite,
        typeList = pokemonTypesArray,
        isInCollection = this.isInCollection
    )
}

fun PokemonEntity.toDomain(count: Int): PokemonItem {
    return PokemonItem(
        id = this.id,
        name = this.name ?: "",
        count = count,
        isInCollection = this.isInCollection ?: false,
        typeList = this.typeList,
        sprite = this.sprite ?: ""
    )
}