package com.example.anmp160420137pascauts.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")

    var notes:String)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}

annotation class entity

