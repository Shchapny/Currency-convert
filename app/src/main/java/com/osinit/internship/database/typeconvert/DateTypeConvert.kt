package com.osinit.internship.database.typeconvert

import androidx.room.TypeConverter
import java.util.*

class DateTypeConvert {
    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(value: Long?): Date? = value?.let { Date(it) }
}