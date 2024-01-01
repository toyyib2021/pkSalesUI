package com.pureKnowledge.salesApp.util

import java.util.Locale

fun generateAlphallumeric(length: Int): String {
    val LETTERS = "abcdefghijklmnopqrstuwaxyz"
    val NUMBER = "0123456789"
    val ALPHAIRMERIC =
        (LETTERS + LETTERS.uppercase(Locale.getDefault()) + NUMBER).toCharArray()
    val result = StringBuilder()
    for (i in 0 until length) {
        result.append(ALPHAIRMERIC[java.util.Random().nextInt(ALPHAIRMERIC.size)])
    }
    return result.toString()
}


fun generateNumericString(length: Int): String {
    val NUMBERS = "0123456789"
    val numericChars = NUMBERS.toCharArray()
    val result = StringBuilder()

    for (i in 0 until length) {
        result.append(numericChars[java.util.Random().nextInt(numericChars.size)])
    }

    return result.toString()
}
