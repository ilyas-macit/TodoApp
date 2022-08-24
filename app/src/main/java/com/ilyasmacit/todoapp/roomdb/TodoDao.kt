package com.ilyasmacit.todoapp.roomdb

import androidx.room.*
import com.ilyasmacit.todoapp.models.Todo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface TodoDao  {
    @Query("SELECT * FROM Todo")
    fun getAll() : Flowable<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getAllById(id : Int) : Flowable<Todo>

    @Insert
    fun insert(todo : Todo) : Completable

    @Delete
    fun delete(todo : Todo) : Completable

    @Update
    fun update(todo : Todo) : Completable

}