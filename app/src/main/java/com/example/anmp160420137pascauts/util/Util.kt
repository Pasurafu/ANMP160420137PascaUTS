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
            // Adding the is_done column
            database.execSQL(
                "ALTER TABLE todo ADD COLUMN is_done INTEGER"
            ) // because INTEGER is often used for Boolean values (0 and 1) in SQLite for compatibility and efficiency.

            // Setting the default value to 0 and making it non-null
            database.execSQL(
                "UPDATE todo SET is_done = 0 WHERE is_done IS NULL"
            )
            database.execSQL(
                "ALTER TABLE todo ALTER COLUMN is_done SET DEFAULT 0"
            )
            database.execSQL(
                "ALTER TABLE todo ALTER COLUMN is_done SET NOT NULL"
            )
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
