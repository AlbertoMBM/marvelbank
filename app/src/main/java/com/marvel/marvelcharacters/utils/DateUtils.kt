package com.marvel.marvelcharacters.utils

import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_SERVICE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss-SSS"
const val PATTERN_DATA = "dd/MM/yyyy HH:mm:ss"

fun String.transformDateFromServiceToDate(pattern: String = PATTERN_SERVICE_FORMAT): Date {
    val formatDate = SimpleDateFormat(pattern, Locale.getDefault())
    return formatDate.parse(this) ?: Date()
}

fun Date.showDateWithPattern(pattern: String = PATTERN_DATA): String {
    val formatDate = SimpleDateFormat(pattern, Locale.getDefault())
    return formatDate.format(this)
}