package com.github.shtef21.businessdiary.logic

import com.github.shtef21.businessdiary.entities.DiaryLog
import com.github.shtef21.businessdiary.logic.AppProperties.dbTableName
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.util.UUID

fun dbAddOrUpdateLog(
    log: DiaryLog,
    onSuccess: () -> Unit,
    onDatabaseError: (DatabaseError) -> Unit
) {
    val database = Firebase.database.reference

    database
        .child(dbTableName)
        .child(log.logId.toString())
        .setValue(
            log
        ) { err, _ ->
            if (err != null) {
                onDatabaseError(err)
            }
            else {
                onSuccess()
            }
        }
}

fun dbDeleteLog(
    logId: String,
    onSuccess: () -> Unit,
    onDatabaseError: (DatabaseError) -> Unit
) {
    val database = Firebase.database.reference

    database
        .child(dbTableName)
        .child(logId)
        .removeValue { err, _ ->
            if (err != null) {
                onDatabaseError(err)
            }
            else {
                onSuccess()
            }
        }
}

fun dbListenForLogChanges(onDataChange: (List<DiaryLog>) -> Unit, onCancelled: (DatabaseError) -> Unit = {}) {
    val database = Firebase.database.reference
    val tableRef = database.child(dbTableName)

    // Listen for changes in log list
    tableRef.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(logsSnapshot: DataSnapshot) {
            val logs = logsSnapshot.children.map {
                it.getValue(DiaryLog::class.java)!!
            }
            onDataChange(logs)
        }
        override fun onCancelled(error: DatabaseError) {
            onCancelled(error)
        }
    })


}
