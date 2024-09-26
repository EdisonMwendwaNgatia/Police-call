package com.example.policecall

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EnterDetailsActivity : AppCompatActivity() {

    private lateinit var caseType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_details)

        // Get the case type from the intent
        caseType = intent.getStringExtra("CASE_TYPE") ?: ""

        // Finding input fields by their IDs
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitDetails)

        // Set a click listener for the submit button
        btnSubmit.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val location = etLocation.text.toString()

            if (phoneNumber.isBlank() || location.isBlank()) {
                Toast.makeText(this, "Please enter both phone number and location", Toast.LENGTH_SHORT).show()
            } else {
                saveCaseDetails(phoneNumber, location)
            }
        }
    }

    private fun saveCaseDetails(phoneNumber: String, location: String) {
        // Insert reported case with phone number and location into the database
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TYPE, caseType)
            put(DatabaseHelper.COLUMN_PHONE_NUMBER, phoneNumber)
            put(DatabaseHelper.COLUMN_LOCATION, location)
        }
        db.insert(DatabaseHelper.TABLE_CASES, null, values)
        db.close()

        // Show a toast message indicating the case has been reported
        Toast.makeText(this, "Case Reported: $caseType", Toast.LENGTH_SHORT).show()

        // Close the activity after reporting the case
        finish()
    }

    companion object {
        fun newIntent(activity: Activity, caseType: String): Intent {
            return Intent(activity, EnterDetailsActivity::class.java).apply {
                putExtra("CASE_TYPE", caseType)
            }
        }
    }
}
