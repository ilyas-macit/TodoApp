package com.ilyasmacit.todoapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyasmacit.todoapp.models.Todo


@Database(entities = [Todo::class] , version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}