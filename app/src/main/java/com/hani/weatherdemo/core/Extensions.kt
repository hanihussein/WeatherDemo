package com.hani.weatherdemo.core

import java.util.*

val snakeRegex = "_[a-zA-Z]".toRegex()

fun String.snakeToLowerCamelCase(): String {
    return snakeRegex.replace(this) {
        it.value.replace("_", " ")
            .toUpperCase(Locale.ROOT)
    }
}

fun String.snakeToUpperCamelCase(): String {
    return this.snakeToLowerCamelCase().capitalize(Locale.ROOT)
}
