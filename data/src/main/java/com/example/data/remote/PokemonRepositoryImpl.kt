package com.example.data.remote

import com.example.data.local.dao.PokemonDao
import com.example.data.local.model.toDomain
import com.example.data.remote.api.PokeApi
import com.example.data.remote.model.Pokemon
import com.example.data.remote.model.SimplePokemon
import com.example.data.remote.model.toEntity
import com.example.domain.model.PokemonItem
import com.example.domain.repository.PokemonRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    private val _pokemonList = MutableStateFlow<List<SimplePokemon>>(emptyList())
    private val pokemonList = _pokemonList.asStateFlow()

    private val nextPage = MutableSharedFlow<String?>()

    init {
        getPokemonList()
        insertPokemonToDatabase()
    }



    private fun getPokemonList() {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = pokeApi.getAllPokemon()) {
                is NetworkResponse.Success -> {
                    if (response.body.next != null) {
                        nextPage.emit(response.body.next)
                        _pokemonList.emit(_pokemonList.value.plus(response.body.results).distinctBy {it.name})
                    } else {
                        nextPage.emit(null)
                    }
                }

                is NetworkResponse.ServerError -> {
                    //nothing, just inform user of error through ui
                    println(response.body?.message ?: "Server Error")
                }

                is NetworkResponse.NetworkError -> {
                    //plan getAllPokemon for later when network is ok
                    println(response.body?.message ?: "Network Error")
                }

                is NetworkResponse.UnknownError -> {
                    //nothing, just inform user of error through ui
                    println(response.body?.message ?: "Unknown Error")
                }
            }
        }
    }

    private fun insertPokemonToDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            pokemonList.collectLatest { pokemonList ->
                //pokemonDao.addPokemonList(pokemonList.map { it.toEntity() })
            }
        }
    }

    override suspend fun getAllPokemon(): Flow<List<PokemonItem>> {
        return pokemonDao.getAllPokemon().map { pokemonList -> pokemonList.map { pokemon -> pokemon.toDomain(0) } }
    }
}