package com.ilyasmacit.todoapp.models

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Todo(
    @ColumnInfo(name = "name")
    var name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "state")
    var state: String = "new"

)