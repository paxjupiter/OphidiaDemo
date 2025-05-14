package com.cryptid.ophidia.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptid.ophidia.data.Snake

/**
 * Displays user-saved snakes retrieved from local storage.
 * Shows an empty state message if no saved entries exist.
 */
class MySnakesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SnakeAdapter
    private val snakeList = mutableListOf<Snake>()
    private var hasLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_snakes, container, false)

        recyclerView = view.findViewById(R.id.mySnakesRecyclerView)
        adapter = SnakeAdapter(
            snakeList,
            onClick = { snake ->
                // Navigate to snake detail screen
                val fragment = SnakeDetailFragment.newInstance(snake)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            showAddButton = false
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMySnakes()
    }

    /**
     * Loads saved Snake objects from SharedPreferences.
     * Displays a message if none are found.
     */
    private fun loadMySnakes() {
        val sharedPreferences = requireContext().getSharedPreferences("MySnakes", Context.MODE_PRIVATE)
        val savedSnakeJsonSet = sharedPreferences.getStringSet("mySnakesList", emptySet()) ?: emptySet()

        val emptyMessage = view?.findViewById<TextView>(R.id.emptyListMessage)
        if (savedSnakeJsonSet.isEmpty()) {
            emptyMessage?.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            return
        } else {
            emptyMessage?.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

        snakeList.clear()
        savedSnakeJsonSet.forEach { snakeJson ->
            // Deserialize JSON string into Snake object (logic omitted)
        }

        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = requireContext().getSharedPreferences("MySnakes", Context.MODE_PRIVATE)
        val hasNewSnakes = sharedPreferences.getBoolean("hasNewSnakes", false)

        if (hasNewSnakes) {
            hasLoaded = false
            loadMySnakes()
            sharedPreferences.edit().putBoolean("hasNewSnakes", false).apply()
        }
    }
}
