package com.parg3v.ticketsearchapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleLarge = TextStyle(                  /* Title 1 */
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleMedium = TextStyle(                 /* Title 2 */
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleSmall = TextStyle(                  /* Title 3 */
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineMedium = TextStyle(              /* Title 4 */
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic
    ),
    bodyLarge = TextStyle(                   /* Text 1 */
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(                  /* Text 2 */
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(                 /* Button Text */
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelSmall = TextStyle(                  /* Tab text */
        fontSize = 9.sp // some screens need NavBarItems to be smaller
    )
)