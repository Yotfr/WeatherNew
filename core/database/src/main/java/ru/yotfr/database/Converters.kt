package ru.yotfr.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    val moshi = Moshi.Builder().build()

    val stringType = Types.newParameterizedType(List::class.java, String::class.java)
    val stringAdapter = moshi.adapter<List<String>>(stringType)

    val doubleType = Types.newParameterizedType(Double::class.java, String::class.java)
    val doubleAdapter = moshi.adapter<List<Double>>(doubleType)

    val intType = Types.newParameterizedType(Int::class.java, String::class.java)
    val intAdapter = moshi.adapter<List<Int>>(intType)

    @TypeConverter
    fun stringToJson(value: List<String>): String {
        return stringAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToString(value: String): List<String> {
        return stringAdapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun intToJson(value: List<Int>): String {
        return intAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToInt(value: String): List<Int> {
        return intAdapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun doubleToJson(value: List<Double>): String {
        return doubleAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToDouble(value: String): List<Double> {
        return doubleAdapter.fromJson(value) ?: emptyList()
    }
}