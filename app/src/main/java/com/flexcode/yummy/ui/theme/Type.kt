package com.flexcode.yummy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flexcode.yummy.R

val Lato = FontFamily(
    Font(R.font.lato)
)
val LatoBold = FontFamily(
    Font(R.font.lato_bold)
)
val ChunkFive = FontFamily(
    Font(R.font.chunk_five_print)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = ChunkFive,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = LatoBold,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    button = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
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
    */
)
