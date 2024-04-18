package com.github.shtef21.businessdiary.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.shtef21.businessdiary.logic.AppProperties.dbTableName
import com.github.shtef21.businessdiary.logic.DiaryLog
import com.github.shtef21.businessdiary.logic.dbTestAddSomeUser
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

@Composable
fun testing_firebaseUI(context: Context) {

    // on below line creating variable for message.
    val message = remember {
        mutableStateOf("No data")
    }
    // on below line creating variable for firebase
    // database and table reference.
    val database = Firebase.database.reference
    val tableRef = database.child(dbTableName)

    // on below line adding value event listener for database reference.
    tableRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(logListSnapshot: DataSnapshot) {
            val list : MutableList<DiaryLog> = mutableListOf()
            val children = logListSnapshot!!.children
            children.forEach {
                val log = it.getValue(DiaryLog::class.java)!!
                list.add(log)
            }

            // after getting the value we are setting
            // our value to message.
            message.value = "Found ${list.size} items\n" +
                    "${list.map { it.title }}"!!
        }

        override fun onCancelled(error: DatabaseError) {
            // calling on cancelled method when we receive
            // any error or we are not able to get the data.
            Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show()
        }
    })

    // on below line creating a column
    // to display our retrieved text.
    Column(
        // adding modifier for our column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),
        // on below line adding vertical and
        // horizontal alignment for column.
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                dbTestAddSomeUser()
            }
        ) {
            Text("Add some user")
        }

        // on below line adding a text
        // for displaying heading.
        Text(
            textAlign = TextAlign.Center,
            // on below line adding a text message.
            text = "Retrieve Data from Firebase Realtime Database in Android",
            // on below line we are setting text color
            color = Color.Green,
            ////color = greenColor,

            // on below line we are specifying font weight
            fontWeight = FontWeight.Bold,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,

            // on below line we are specifying
            // font size and padding from all sides.
            fontSize = 18.sp, modifier = Modifier.padding(5.dp)
        )
        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line adding a text
        // to display retrieved message.
        Text(
            // on below line setting text
            // message from message variable.
            text = message.value,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
