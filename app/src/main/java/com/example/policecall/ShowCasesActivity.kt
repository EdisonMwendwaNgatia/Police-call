package com.example.policecall

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowCasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cases)

        val tvCases = findViewById<TextView>(R.id.tvCases)

        // Fetch cases from the database
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_CASES, null, null, null, null, null, null
        )

        // Display the cases in the TextView
        val stringBuilder = StringBuilder()
        while (cursor.moveToNext()) {
            val caseType = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE))
            val phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE_NUMBER))
            val location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION))
            stringBuilder.append("Case: $caseType\nPhone: $phoneNumber\nLocation: $location\n\n")
        }
        cursor.close()
        db.close()

        tvCases.text = stringBuilder.toString()
    }
}
