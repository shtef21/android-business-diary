package com.github.shtef21.businessdiary.entities

import androidx.compose.ui.graphics.Color
import com.github.shtef21.businessdiary.ui.theme.BlueLog
import com.github.shtef21.businessdiary.ui.theme.GreenLog
import com.github.shtef21.businessdiary.ui.theme.OrangeLog
import com.github.shtef21.businessdiary.ui.theme.RedLog

enum class Difficulty(val color: Color) {
    Easy(GreenLog),
    Normal(OrangeLog),
    Difficult(RedLog),
    Unset(BlueLog),
    Unknown(Color.Gray)
}
