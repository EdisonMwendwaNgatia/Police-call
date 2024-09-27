package com.example.policecall

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EnterDetailsActivity : AppCompatActivity() {

    private lateinit var caseType: String
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_details)

        // Get the case type from the intent
        caseType = intent.getStringExtra("CASE_TYPE") ?: ""

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().reference.child("cases")

        // Find input fields by their IDs
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
        // Create a unique key for each case
        val caseId = database.push().key ?: return

        // Create a case object to store in Firebase
        val caseData = mapOf(
            "type" to caseType,
            "phoneNumber" to phoneNumber,
            "location" to location
        )

        // Save the case to Firebase
        database.child(caseId).setValue(caseData)
            .addOnSuccessListener {
                Toast.makeText(this, "Case Reported: $caseType", Toast.LENGTH_SHORT).show()
                finish() // Close the activity after reporting the case
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to report case. Try again.", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        fun newIntent(activity: Activity, caseType: String): Intent {
            return Intent(activity, EnterDetailsActivity::class.java).apply {
                putExtra("CASE_TYPE", caseType)
            }
        }
    }
}
