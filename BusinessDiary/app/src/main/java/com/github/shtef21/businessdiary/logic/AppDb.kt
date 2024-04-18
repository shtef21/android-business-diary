package com.github.shtef21.businessdiary.logic

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.shtef21.businessdiary.logic.AppProperties.dbTableName
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

fun dbAddOrUpdateLog(
    log: DiaryLog,
    onSuccess: () -> Unit,
    onDatabaseError: (DatabaseError) -> Unit
) {

    // TODO: add auth
    val database = Firebase.database.reference

    database
        .child(dbTableName)
        .child(log.logId)
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

fun dbListenForLogChanges(onDataChange: (List<DiaryLog>) -> Unit, onCancelled: (DatabaseError) -> Unit = {}) {
    // TODO: add auth

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
