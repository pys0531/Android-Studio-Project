package com.example.superheroes.ui.theme

import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.unit.sp
import com.example.superheroes.R

val Calbin = FontFamily(
    Font(R.font.cabin_condensed_regular, FontWeight.Normal),
    Font(R.font.cabin_condensed_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Calbin,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Calbin,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Calbin,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Calbin,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)