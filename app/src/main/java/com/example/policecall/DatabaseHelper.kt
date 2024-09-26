package com.example.policecall

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2 // Incremented version for schema changes
        private const val DATABASE_NAME = "reported_cases.db"

        // Define table and column names
        const val TABLE_CASES = "cases"
        const val COLUMN_ID = "_id"
        const val COLUMN_TYPE = "type"
        const val COLUMN_PHONE_NUMBER = "phone_number"
        const val COLUMN_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the table for reported cases with additional columns for phone number and location
        val createTableQuery = ("CREATE TABLE $TABLE_CASES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_TYPE TEXT,"
                + "$COLUMN_PHONE_NUMBER TEXT,"
                + "$COLUMN_LOCATION TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades
        if (oldVersion < 2) {
            // Add phone_number and location columns if upgrading from version 1 to 2
            db.execSQL("ALTER TABLE $TABLE_CASES ADD COLUMN $COLUMN_PHONE_NUMBER TEXT")
            db.execSQL("ALTER TABLE $TABLE_CASES ADD COLUMN $COLUMN_LOCATION TEXT")
        }
    }

    // You can add CRUD methods if necessary, like fetching or updating cases
}
