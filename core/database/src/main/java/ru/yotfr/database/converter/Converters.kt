package ru.yotfr.database.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    val moshi = Moshi.Builder().build()

    val stringType = Types.newParameterizedType(List::class.java, String::class.java)
    val stringAdapter = moshi.adapter<List<String>>(stringType)

    val doubleType = Types.newParameterizedType(List::class.java, Double::class.javaObjectType)
    val doubleAdapter = moshi.adapter<List<Double>>(doubleType)

    val intType = Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
    val intAdapter = moshi.adapter<List<Int>>(intType)

    @TypeConverter
    fun stringToJson(value: List<String>?): String? {
        return if (value == null) null else stringAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToString(value: String?): List<String>? {
        return if (value == null) null else stringAdapter.fromJson(value)
    }

    @TypeConverter
    fun intToJson(value: List<Int>?): String? {
        return if (value == null) null else intAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToInt(value: String?): List<Int>? {
        return if (value == null) null else intAdapter.fromJson(value)
    }

    @TypeConverter
    fun doubleToJson(value: List<Double>?): String? {
        return if (value == null) null else doubleAdapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToDouble(value: String?): List<Double>? {
        return if (value == null) null else doubleAdapter.fromJson(value)
    }
}