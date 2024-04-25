package com.github.shtef21.businessdiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.shtef21.businessdiary.ui.theme.LogDesc

@Composable
fun LogCard(title: String, description: String, cardColor: Color) {
    val cardHeight = 80.dp
    val labelHeight = cardHeight - 20.dp

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(cardHeight)
                .offset(0.dp, 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(3.dp, labelHeight)
                    .clip(
                        RoundedCornerShape(
                            0.dp, 8.dp, 8.dp, 0.dp
                        )
                    )
                    .background(cardColor)
            )
            Box(
                modifier = Modifier
                    .offset(8.dp, -10.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "test",
                    tint = cardColor,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
            Box(
                modifier = Modifier
                    .offset(48.dp, 0.dp)
                    .padding(0.dp, 2.dp, 0.dp, 5.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        color = cardColor,
                        fontSize = 20.sp
                    )
                    Text(
                        text = description,
                        color = LogDesc,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}