package com.github.shtef21.businessdiary.logic

import com.github.shtef21.businessdiary.logic.AppProperties.dbTableName
import com.github.shtef21.businessdiary.logic.AppProperties.dbUrl
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

fun dbAddOrUpdateLog(
    log: DiaryLog,
    onSuccess: () -> Unit,
    onFailure: (Exception) -> Unit
) {

    // TODO: add auth
    val database = Firebase.database(dbUrl).reference

    database
        .child(dbTableName).child(log.logId).setValue(log)
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener {
            onFailure(it)
        }
}

fun dbReadLogs(onDataChange: (List<DiaryLog>) -> Unit, onCancelled: (DatabaseError) -> Unit) {
    // TODO: add auth
    val database = Firebase.database(dbUrl).reference

    val logsRef = database.child(dbTableName)

    val logListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val logs = mutableListOf<DiaryLog>()
            for (snapshot in dataSnapshot.children) {
                val log = snapshot.getValue<DiaryLog>()
                log?.let {
                    logs.add(it)
                }
            }
            onDataChange(logs)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            onCancelled(databaseError)
        }
    }

    logsRef.addValueEventListener(logListener)
}
