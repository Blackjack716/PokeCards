package com.example.pokecards.ui.compose


data class CharacterListState(
    //val characters: List<CharacterItem> = emptyList(),
    val listId: ListType = ListType.AllCharacterList,
)

enum class ListType {
    AllCharacterList, FavouriteList
}