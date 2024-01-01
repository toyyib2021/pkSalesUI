package com.pureKnowledge.salesApp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightThemeColours = lightColorScheme(
    primary = Color.Black,
    secondary = Color.Blue,
    background = Color.White
)

val darkThemeColours = darkColorScheme(
    primary = Color.Red,
    secondary = Color.Red,
    background = Color.Black
)



@Composable
fun PKSalesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if (darkTheme) darkThemeColours else lightThemeColours,
        typography = Typography(),
        content = content
//            Text(text = "Pure Knowledge", style = MaterialTheme.typography.bodyLarge)

    )

}


