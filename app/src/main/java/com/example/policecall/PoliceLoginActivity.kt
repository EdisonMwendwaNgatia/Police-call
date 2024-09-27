package com.example.policecall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PoliceLoginActivity : AppCompatActivity() {

    // Hardcode valid badge numbers and names u can add them if too many we can create an authentication database where they can be added to
    private val validBadgeNumbers = mapOf("12345" to "Officer John", "54321" to "Officer Jane")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_police_login)

        val etBadgeNumber = findViewById<EditText>(R.id.etBadgeNumber)
        val etOfficerName = findViewById<EditText>(R.id.etOfficerName)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val badgeNumber = etBadgeNumber.text.toString()
            val officerName = etOfficerName.text.toString()

            if (isValidCredentials(badgeNumber, officerName)) {
                // Successful login
                val intent = Intent(this, ShowCasesActivity::class.java)
                startActivity(intent)
            } else {
                // Show error
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Check if the entered badge number and name match the hardcoded values
    private fun isValidCredentials(badgeNumber: String, officerName: String): Boolean {
        return validBadgeNumbers[badgeNumber] == officerName
    }
}
