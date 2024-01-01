package com.pureKnowledge.salesApp.util

import androidx.compose.runtime.MutableState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun dateChecker(
    inputDateText: MutableState<String>,
    currentDate: LocalDate?,
): Boolean {
    val inputDate = try {
        LocalDate.parse(inputDateText.value, DateTimeFormatter.ISO_DATE)
    } catch (e: Exception) {
        null
    }

    var subscriptionLimit = true

    if (inputDate != null) {
        val comparisonResult = inputDate.compareTo(currentDate)

        when {
            comparisonResult < 0 -> {
                // Sub Expired
                subscriptionLimit = false
            }

            comparisonResult == 0 -> {
//                currentTerm.value = "last day of the term"

            }

            else -> {
                // Sub is active
                subscriptionLimit = true
            }
        }
    }
    return subscriptionLimit
}