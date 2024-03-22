package com.github.shtef21.businessdiary.logic

import com.github.shtef21.businessdiary.logic.AppProperties.dbUrl
import com.google.firebase.Firebase
import com.google.firebase.database.database

fun addDiaryLogToDatabase(log: DiaryLog) {

    // TODO: add auth
    val database = Firebase.database(dbUrl)

    val myRef = database.getReference("diaryLogs")

    val dbItem = mutableMapOf<String, Any>()
    dbItem["title"] = log.title
    dbItem["description"] = log.description

    val childRef = myRef.push()
    childRef.setValue(dbItem)
}
