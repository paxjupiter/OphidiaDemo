package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment to display all snakes in a scrollable list.
 * Implements lazy loading with infinite scroll and navigation to detail views.
 */
class ExploreAllFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SnakeAdapter
    private val snakeList = mutableListOf<Snake>()
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore_all, container, false)

        // Set up RecyclerView with adapter
        recyclerView = view.findViewById(R.id.snakeRecyclerView)
        adapter = SnakeAdapter(
            snakeList,
            onClick = { snake ->
                // Navigate to detail view (logic omitted)
            },
            showAddButton = false
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Load initial batch
        loadSnakes()

        // Infinite scroll listener
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (!rv.canScrollVertically(1) && !isLoading) {
                    loadSnakes()
                }
            }
        })

        return view
    }

    private fun loadSnakes() {
        isLoading = true

        // Load data asynchronously from local database
        lifecycleScope.launch {
            val newSnakes = getSnakesFromDb()
            snakeList.addAll(newSnakes)
            adapter.notifyDataSetChanged()
            isLoading = false
        }
    }

    // Actual DB call omitted in public demo
    private suspend fun getSnakesFromDb(): List<Snake> = listOf()
}
