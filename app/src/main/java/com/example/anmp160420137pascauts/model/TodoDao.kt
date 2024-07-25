package com.example.anmp160420137pascauts.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Todo)

    // Select all todos where is_done is 0 to only display incomplete tasks
    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id: Int): Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority WHERE uuid = :id")
    suspend fun update(title: String, notes: String, priority: Int, id: Int)

    // Update the is_done field to 1 to mark the todo as done without deleting it
    @Query("UPDATE todo SET is_done = 1 WHERE uuid = :id")
    suspend fun markAsDone(id: Int)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}
