package com.example.rideapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.sp
import com.example.rideapp.R

// Set of Material typography styles to start with
/*val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
*/


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)*/

private val satoshi = FontFamily(
    Font(R.font.satoshi_regular),
    Font(R.font.satoshi_medium, FontWeight.W500),
    Font(R.font.satoshi_bold, FontWeight.Bold)
)


val typography = Typography(defaultFontFamily = satoshi)


private val fontFamilySatoshi = FontFamily(
    listOf(
        Font(
            resId = R.font.satoshi_regular
        ),
        Font(
            resId = R.font.satoshi_medium,
            weight = FontWeight.Normal
        )
    )
)

val SatoshiTypography = Typography(
    body1 = TextStyle(
        fontFamily = fontFamilySatoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = fontFamilySatoshi,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        letterSpacing = (0).sp
    ),

    body2 = TextStyle(
        fontSize = 16.sp,
        fontWeight = W700,
        letterSpacing = 0.sp
    ),
    caption = TextStyle(
        fontFamily = fontFamilySatoshi,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp

    ),
)