package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cryptid.ophidia.data.Snake
import kotlinx.coroutines.launch

/**
 * Core container fragment for the snake identification flow.
 * Hosts a ViewPager for filter steps and handles user input persistence and result generation.
 */
class IdSnakeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private val viewModel: IdSnakeViewModel by lazy {
        ViewModelProvider(requireActivity())[IdSnakeViewModel::class.java]
    }

    private val snakeList = mutableListOf<Snake>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_id_snake, container, false)

        // ViewPager hosts the multi-step ID flow
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = IdSnakePagerAdapter(this)

        return view
    }

    fun navigateToStep(stepIndex: Int) {
        viewPager.currentItem = stepIndex
    }

    fun navigateToResults() {
        // Pass filtered snake list to ResultsFragment
        // Logic omitted for demo
    }

    /**
     * Called once user has completed the ID flow.
     * Filters the full snake dataset based on selected inputs and displays results.
     */
    fun onUserActionComplete() {
        lifecycleScope.launch {
            val filteredSnakes = searchSnakes()
            snakeList.clear()
            snakeList.addAll(filteredSnakes)
            navigateToResults()
        }
    }

    /**
     * Applies user input filters to local database and returns matching snakes.
     */
    private suspend fun searchSnakes(): List<Snake> {
        // Logic includes weighted scoring system across multiple fields
        // Actual match algorithm omitted in public demo
        return listOf()
    }
}
