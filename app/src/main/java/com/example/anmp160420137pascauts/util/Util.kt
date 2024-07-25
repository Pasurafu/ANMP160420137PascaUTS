package com.example.anmp160420137pascauts.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context): TodoDatabase {
    // Migration from version 1 to version 2: adding priority column
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL"
            )
        }
    }

    // New migration from version 2 to version 3: adding is_done column
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL"
            ) // because INTEGER is often used for Boolean values (0 and 1) in SQLite for compatibility and efficiency.
        }
    }

    val db = Room.databaseBuilder(
        context,
        TodoDatabase::class.java, DB_NAME
    )
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()

    return db
}
