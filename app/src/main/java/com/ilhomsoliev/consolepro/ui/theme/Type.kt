package com.ilhomsoliev.consolepro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.consolepro.R

private val SourceCodePro = FontFamily(
    Font(R.font.sourcecodepro_regular),
    Font(R.font.sourcecodepro_semibold, FontWeight.SemiBold)
)

private val SourceCodeProItalic = FontFamily(
    Font(R.font.sourcecodepro_italic),
    Font(R.font.sourcecodepro_italic, FontWeight.SemiBold)
)

val Typography = Typography(

    displayLarge = TextStyle( // 1
        fontFamily = SourceCodePro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 100.sp
    ),
    displayMedium = TextStyle( // 2
        fontFamily = SourceCodePro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    displaySmall = TextStyle( // 3
        fontFamily = SourceCodePro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle( // 4
        fontFamily = SourceCodeProItalic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    )
)
