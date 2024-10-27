package com.example.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun toListFromString(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringFromList(value: String): java.util.ArrayList<String> = Gson().fromJson(value, ArrayList<String>()::class.java)
}