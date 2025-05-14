package com.cryptid.ophidia.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * LoginActivity serves as the entry point of the app.
 * Initializes the interface and handles user continuation.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content view using ViewBinding (actual layout omitted)

        // On button press, transition to the main dashboard
        setupContinueButton()
    }

    private fun setupContinueButton() {
        // Listens for user tap and opens MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
