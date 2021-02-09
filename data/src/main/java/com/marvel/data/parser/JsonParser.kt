package com.marvel.data.parser

interface JsonParser {
    fun <T> fromJson(json: String, type: Class<T>): T?
    fun <T : Any> toJson(value: T): String
}