package com.example.policecall

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ShowCasesActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var tvCases: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cases)

        tvCases = findViewById(R.id.tvCases)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().reference.child("cases")

        // Fetch cases from Firebase
        fetchCases()
    }

    private fun fetchCases() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stringBuilder = StringBuilder()

                for (caseSnapshot in snapshot.children) {
                    val caseType = caseSnapshot.child("type").value.toString()
                    val phoneNumber = caseSnapshot.child("phoneNumber").value.toString()
                    val location = caseSnapshot.child("location").value.toString()

                    stringBuilder.append("Case: $caseType\nPhone: $phoneNumber\nLocation: $location\n\n")
                }

                tvCases.text = stringBuilder.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                tvCases.text = getString(R.string.failed_to_load_cases)
            }
        })
    }
}
