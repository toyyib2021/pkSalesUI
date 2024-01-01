package com.pureKnowledge.salesApp.util

fun truncateString(input: String, maxLength: Int): String {
    return if (input.length > maxLength) {
        input.substring(0, maxLength - 3) + "..."
    } else {
        input
    }
}

fun percentageCalculator(amount: Int, percent: Int): String {
    val percentage = percent / 100

    val value = (amount * percent/100).toString()

    return value
}