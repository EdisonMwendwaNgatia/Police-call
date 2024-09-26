package com.example.policecall


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val ENTER_DETAILS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find buttons by their IDs
        val btnRape = findViewById<Button>(R.id.btnRape)
        val btnRobbery = findViewById<Button>(R.id.btnRobbery)
        // Add a new button to show cases
        val btnShowCases = findViewById<Button>(R.id.btnShowCases)

        // Setting click listeners for each button
        btnRape.setOnClickListener {
            startActivityForResult(EnterDetailsActivity.newIntent(this, "Rape"), ENTER_DETAILS_REQUEST_CODE)
        }

        btnRobbery.setOnClickListener {
            startActivityForResult(EnterDetailsActivity.newIntent(this, "Robbery"), ENTER_DETAILS_REQUEST_CODE)
        }

        // Navigate to PoliceLoginActivity when Show Cases button is clicked
        btnShowCases.setOnClickListener {
            val intent = Intent(this, PoliceLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
