package ru.yotfr.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
    val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(type)

    @TypeConverter
    fun mapToJson(value: Map<String, String>?): String? {
        return if (value == null) null else adapter.toJson(value)
    }

    //TODO: null
    @TypeConverter
    fun fromJsonToMap(value: String?): Map<String, String>? {
        return if (value == null) null else adapter.fromJson(value)!!
    }
}