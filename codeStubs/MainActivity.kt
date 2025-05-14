package com.cryptid.ophidia.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cryptid.ophidia.R
import com.cryptid.ophidia.data.SnakeDatabase
import kotlinx.coroutines.launch

/**
 * Hosts all fragments and handles top navigation routing.
 * Initializes the Room database and provides scoped coroutine access.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize local Room database and snake DAO
        val db = SnakeDatabase.getDatabase(applicationContext)
        val snakeDao = db.snakeDao()

        // Example coroutine for startup DB read (debug-only in dev)
        lifecycleScope.launch {
            val allSnakes = snakeDao.getAll()
            // Logging/debug behavior omitted in demo
        }

        // Load default fragment
        loadFragment(HomeFragment())

        // Set up top nav image click listeners
        findViewById<ImageView>(R.id.nav_home).setOnClickListener {
            loadFragment(HomeFragment())
        }
        findViewById<ImageView>(R.id.nav_id_snake).setOnClickListener {
            loadFragment(IdSnakeFragment())
        }
        findViewById<ImageView>(R.id.nav_explore_all).setOnClickListener {
            loadFragment(ExploreAllFragment())
        }
        findViewById<ImageView>(R.id.nav_my_snakes).setOnClickListener {
            loadFragment(MySnakesFragment())
        }
        findViewById<ImageView>(R.id.nav_about).setOnClickListener {
            loadFragment(AboutOphidiaFragment())
        }
    }

    /**
     * Handles fragment swapping via FragmentManager.
     */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
