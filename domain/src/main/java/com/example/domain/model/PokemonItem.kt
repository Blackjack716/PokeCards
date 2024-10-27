package com.example.domain.model

data class PokemonItem(
    val id: Int,
    val name: String,
    val typeList: List<String>,
    val sprite: String,
    val isInCollection: Boolean,
    val count: Int
)
