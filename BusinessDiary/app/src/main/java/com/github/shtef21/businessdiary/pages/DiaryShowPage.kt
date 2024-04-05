package com.github.shtef21.businessdiary.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.dbReadLogs
import com.github.shtef21.businessdiary.ui.theme.AppBackground
import com.github.shtef21.businessdiary.ui.theme.BlueLog
import com.github.shtef21.businessdiary.ui.theme.LogDesc

@Composable
fun DiaryShowContainer() {


    var logs: List<DiaryLog> by remember {
        mutableStateOf(emptyList())
    }

    // TODO: fix this part
    /* dbReadLogs(
        onDataChange = { logsUpdate ->
            logs = logsUpdate
        },
        onCancelled = { dbError ->
            // Does nothing
        }
    )*/

    Box(
        modifier = Modifier
    ) {
        Surface(
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LogCard("Log title", "Log description ".repeat(2))
                LogCard("Log title", "Log description ".repeat(2))
            }

            // TODO: fix this part
            /*LazyColumn {
                items(logs) { log ->
                    LogCard(title = log.title, description = log.description)
                }
            }*/
        }
    }
}

@Composable
fun LogCard(title: String, description: String) {
    val cardHeight = 80.dp
    val labelHeight = cardHeight - 20.dp

    Card {
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
                    .background(BlueLog)
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
                    tint = BlueLog,
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
                        color = BlueLog,
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

@Preview
@Composable
fun PreviewDiaryShow() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black))
            .fillMaxSize()
    ) {
        DiaryShowContainer()
    }
}
