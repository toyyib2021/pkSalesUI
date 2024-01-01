package com.pureKnowledge.salesApp.util


import androidx.compose.ui.platform.LocalContext
import com.teamapt.monnify.sdk.Monnify
import com.teamapt.monnify.sdk.data.model.TransactionDetails
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit



fun calculateDaysBetween(
    startDateYear: Int, startDateMonth: Int, startDateDay: Int,
    endDateYear: Int, endDateMonth: Int, endDateDay: Int,
): Int {

    val startDate = LocalDate.of(startDateYear, startDateMonth, startDateDay)
    val endDate = LocalDate.of(endDateYear, endDateMonth, endDateDay) // Note: Assuming 2024 is a leap year

    val daysBetween = ChronoUnit.DAYS.between(startDate, endDate)

    return daysBetween.toInt()
}


fun previousDaysCount(givenDate: String, daysToSubtract: Long): String {


    // Parse the given date
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(givenDate, formatter)

    // Subtract days
    val subtractDays = date.minusDays(daysToSubtract)


    // Format the result
    val result = subtractDays.format(formatter)

    println("Given date: $givenDate")
    println("30 days before: $result")

    return result

}


fun futureDaysCount(givenDate: String, monthsToAdd: Long): String {

    // Parse the given date
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(givenDate, formatter)

    // Added days
    val subtractDays = date.plusMonths(monthsToAdd)


    // Format the result
    val result = subtractDays.format(formatter)

    println("Given date: $givenDate")
    println("$monthsToAdd months after: $result")

    return result
}