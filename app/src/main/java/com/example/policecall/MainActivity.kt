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
        val btnAssault = findViewById<Button>(R.id.btnAssault)
        val btnViolence = findViewById<Button>(R.id.btnViolence)
        val btnCorruption = findViewById<Button>(R.id.btnCorruption)
        val btnTrafficOffense = findViewById<Button>(R.id.btnTrafficOffense)
        val btnFraudScams = findViewById<Button>(R.id.btnFraudScams)
        val btnDrugRelated = findViewById<Button>(R.id.btnDrugRelated)

        val btnShowCases = findViewById<Button>(R.id.btnShowCases)

        // Setting click listeners for each button
        btnRape.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Rape"))
        }

        btnRobbery.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Robbery"))
        }

        btnAssault.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Assault"))
        }

        btnViolence.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Violence"))
        }

        btnCorruption.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "Corruption"))
        }

        btnTrafficOffense.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "TrafficOffense"))
        }

        btnFraudScams.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "FraudScams"))
        }

        btnDrugRelated.setOnClickListener {
            startActivity(EnterDetailsActivity.newIntent(this, "DrugRelated"))
        }

        // Navigate to PoliceLoginActivity when Show Cases button is clicked
        btnShowCases.setOnClickListener {
            val intent = Intent(this, PoliceLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
