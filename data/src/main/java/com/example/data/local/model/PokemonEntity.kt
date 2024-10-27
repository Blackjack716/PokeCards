package com.example.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "sprite") val sprite: String?,
    @ColumnInfo(name = "types") val typeList: ArrayList<String>,
    @ColumnInfo(name = "isInCollection") val isInCollection: Boolean?,
)

@Entity
data class PokemonCollectionEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "sprite") val sprite: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "count") val count: Int?,
    @ColumnInfo(name = "isInCollection") val isFavourite: Boolean?,
)
