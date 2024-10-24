package com.example.pokecards.ui.compose

import com.example.domain.model.CharacterItem

data class CharacterListState(
    val characters: List<CharacterItem> = emptyList(),
    val listId: ListType = ListType.AllCharacterList,
)

enum class ListType {
    AllCharacterList, FavouriteList
}