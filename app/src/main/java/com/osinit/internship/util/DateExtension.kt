package com.osinit.internship.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.selectDate(): String {
    val formatDate = SimpleDateFormat("dd MMMM yyyy г.", Locale.getDefault())
    return formatDate.format(this)
}

fun Date.apiDate(): String {
    val formatDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    return formatDate.format(this)
}