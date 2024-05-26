package com.example.anmp160420137pascauts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo")


    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo
    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
            suspend fun update(title:String, notes:String, priority:Int, id:Int)
    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Delete
    fun deleteTodo(todo:Todo)

}
