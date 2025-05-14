package com.cryptid.ophidia.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.cryptid.ophidia.R
import com.cryptid.ophidia.data.SnakeDatabase
import com.cryptid.ophidia.data.SnakeDatabase.Companion.generateListsFromCsv

/**
 * Launch screen that preloads critical data on first run and initializes the local database.
 * Navigates to LoginActivity after a short delay.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize Room database
        prepopulateDatabase()

        // Generate cached lists from CSV on first app launch
        if (isFirstLaunch(this)) {
            generateListsFromCsv(this)
        }

        // Delayed navigation to login screen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    /**
     * Ensures Room initializes by forcing writable database access.
     */
    private fun prepopulateDatabase() {
        val db = SnakeDatabase.getDatabase(applicationContext)
        db.openHelper.writableDatabase // Triggers Room creation
    }

    /**
     * Returns true if this is the first time the app has been launched.
     * Sets a flag to prevent repeat CSV parsing.
     */
    private fun isFirstLaunch(context: Context): Boolean {
        val prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirst = prefs.getBoolean("isFirstLaunch", true)
        if (isFirst) {
            prefs.edit().putBoolean("isFirstLaunch", false).apply()
        }
        return isFirst
    }
}
