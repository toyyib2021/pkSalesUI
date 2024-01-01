package com.pureKnowledge.salesApp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pureknowledge.salesApp.R

val IstokWeb = FontFamily(
    Font(R.font.istok_web_bold, weight = FontWeight.Bold),
    Font(R.font.istok_web_regular, weight = FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = IstokWeb,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = IstokWeb,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontFamily = IstokWeb,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
    )

)

