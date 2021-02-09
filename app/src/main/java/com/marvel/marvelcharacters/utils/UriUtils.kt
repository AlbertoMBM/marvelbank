package com.marvel.marvelcharacters.utils

fun getUri(path: String, size: String, extension: String): String{
    val uri = if (size.isNotBlank()){
        "$path/$size.$extension"
    }else{
        "$path.$extension"
    }
    return uri.replace("http", "https")
}

