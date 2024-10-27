package com.example.pokecards.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.GetAllPokemonUseCase
import com.example.domain.features.GetCollectedPokemonUseCase
import com.example.domain.model.PokemonItem
import com.example.pokecards.ui.compose.CharacterEvent
import com.example.pokecards.ui.compose.CharacterListState
import com.example.pokecards.ui.compose.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val getCollectedPokemonUseCase: GetCollectedPokemonUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonItem>>(emptyList())

    private val _collectedPokemonList = MutableStateFlow<List<PokemonItem>>(emptyList())

    private val listType = MutableStateFlow(ListType.AllCharacterList)

    private val _state = MutableStateFlow(CharacterListState(listType.value))
    val state = _state.asStateFlow()

    init {
        observeCharacterList()
        observeFavouriteList()
        observeListType()
    }

    private fun observeCharacterList() {
        viewModelScope.launch {
            getAllPokemonUseCase.execute().collectLatest {
                _pokemonList.emit(it)
            }
        }
    }

    private fun observeFavouriteList() {
        viewModelScope.launch {
            getCollectedPokemonUseCase.execute().collectLatest {
                _pokemonList.emit(it)
            }
        }
    }

    private fun observeListType() {
        /*viewModelScope.launch {
            combine(
                listType,
                _characterList,
                _favouriteList,
            ) { listType, characterList, favouriteList ->
                Triple(listType, characterList, favouriteList)
            }.collectLatest { (listType, characterList, favouriteList) ->
                when (listType) {
                    ListType.AllCharacterList -> {
                        _state.emit(CharacterListState(characterList, listType))
                    }
                    ListType.FavouriteList -> {
                        _state.emit(CharacterListState(favouriteList, listType))
                    }
                }
            }
        }*/
    }


    fun onEvent(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.OnFavCharacterClicked -> {
                //setFavouriteCharacterUseCase.execute(event.character, event.isFavourite)
            }

            CharacterEvent.OnAllListClicked -> listType.value = ListType.AllCharacterList
            CharacterEvent.OnFavListClicked -> listType.value = ListType.FavouriteList
        }
    }
}