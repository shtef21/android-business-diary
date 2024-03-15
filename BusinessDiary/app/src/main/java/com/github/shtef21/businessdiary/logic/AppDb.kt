package com.github.shtef21.businessdiary.logic

import com.github.shtef21.businessdiary.AppProperties.logic.dbUrl
import com.google.firebase.Firebase
import com.google.firebase.database.database

fun addItemToDatabase() : String {

    val database = Firebase.database(dbUrl)

    val myRef = database.getReference("message/another")
    myRef.setValue("Hello, World!")

    return "Done!";
}
