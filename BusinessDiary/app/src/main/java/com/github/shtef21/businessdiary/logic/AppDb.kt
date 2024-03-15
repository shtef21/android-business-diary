package com.github.shtef21.businessdiary.logic

import com.github.shtef21.businessdiary.logic.AppProperties.dbUrl
import com.google.firebase.Firebase
import com.google.firebase.database.database

fun addItemToDatabase(messageValue: DiaryLog) {

    // TODO: add auth
    val database = Firebase.database(dbUrl)

    val myRef = database.getReference("diaryLogs")

    val testArr = arrayOf(messageValue)
    myRef.setValue(testArr)
}
