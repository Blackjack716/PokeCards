package com.example.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class PokemonDto(
    @SerializedName("count")
    @Expose
    var count: Int? = null,

    @SerializedName("next")
    @Expose
    val next: String? = null,

    @SerializedName("previous")
    @Expose
    val previous: String? = null,

    @SerializedName("results")
    @Expose
    val results: List<SimplePokemon> = emptyList()
)

data class SimplePokemon(
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("url")
    @Expose
    val sprites: List<PokemonSprite> = emptyList(),
)

data class Pokemon(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("sprites")
    @Expose
    val sprites: List<PokemonSprite> = emptyList(),

    @SerializedName("types")
    @Expose
    val types: List<PokemonType> = emptyList(),
)

@Serializable
data class PokemonSprite(

    @SerializedName("front_default")
    @Expose
    val sprite: String? = null,
)

@Serializable
data class PokemonType(

    @SerializedName("slot")
    @Expose
    val slot: Int? = null,

    @SerializedName("type")
    @Expose
    val types: List<Type> = emptyList(),
)

@Serializable
data class Type(

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,
)
