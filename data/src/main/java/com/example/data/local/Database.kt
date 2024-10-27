package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.PokemonDao
import com.example.data.local.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "PokeCards-database"
    }
}