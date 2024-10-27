package com.example.pokecards.ui.compose

import com.example.domain.model.PokemonItem

sealed class CharacterEvent {

    data class OnFavCharacterClicked(val character: PokemonItem, val isFavourite: Boolean) : CharacterEvent()
    data object OnFavListClicked : CharacterEvent()
    data object OnAllListClicked : CharacterEvent()
}