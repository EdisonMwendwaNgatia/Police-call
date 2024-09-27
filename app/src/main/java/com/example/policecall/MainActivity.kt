package com.example.policecall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find buttons by their IDs
        val btnRape = findViewById<Button>(R.id.btnRape)
        val btnRobbery = findViewById<Button>(R.id.btnRobbery)
        val btnShowCases = findViewById<Button>(R.id.btnShowCases)

        // Setting click listeners for each button
        btnRape.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Rape"))
        }

        btnRobbery.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Robbery"))
        }

        // Navigate to PoliceLoginActivity when Show Cases button is clicked
        btnShowCases.setOnClickListener {
            val intent = Intent(this, PoliceLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
