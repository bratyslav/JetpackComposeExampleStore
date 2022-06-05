package com.example.jetpackexamplestore

fun String.isNumeric() = all { char -> char.isDigit() }

fun Float.roundTo(decimalCount: Int) = String
    .format("%.${decimalCount}f", this)
    .replace(',', '.')
    .toFloat()