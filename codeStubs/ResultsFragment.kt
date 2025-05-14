package com.cryptid.ophidia.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptid.ophidia.data.Snake

/**
 * Displays filtered snake results after completing the ID flow.
 * Enables users to navigate to detail screens or save favorites.
 */
class ResultsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SnakeAdapter
    private val snakeList = mutableListOf<Snake>() // Filtered results list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)

        recyclerView = view.findViewById(R.id.resultsRecyclerView)
        adapter = SnakeAdapter(
            snakeList,
            showAddButton = true, // Enable "This is it!" button for saving
            onClick = { snake ->
                // Navigate to detailed view
                val fragment = SnakeDetailFragment.newInstance(snake)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onAddSnake = { snake ->
                addSnakeToMySnakes(snake)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Populate from arguments if not already loaded
        if (snakeList.isEmpty()) {
            arguments?.getParcelableArrayList<Snake>("snakeList")?.let {
                snakeList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }

        return view
    }

    /**
     * Saves selected Snake to SharedPreferences for display in My Snakes.
     * Prevents duplicate entries and provides user feedback.
     */
    private fun addSnakeToMySnakes(snake: Snake) {
        val sharedPreferences = requireContext().getSharedPreferences("MySnakes", Context.MODE_PRIVATE)
        val currentSet = sharedPreferences.getStringSet("mySnakesList", emptySet())?.toMutableSet() ?: mutableSetOf()

        val snakeJson = serializeSnake(snake)
        if (!currentSet.contains(snakeJson)) {
            currentSet.add(snakeJson)
            sharedPreferences.edit()
                .putStringSet("mySnakesList", currentSet)
                .putBoolean("hasNewSnakes", true)
                .apply()
            Toast.makeText(requireContext(), "${snake.commonName} added!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "${snake.commonName} already added.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun serializeSnake(snake: Snake): String {
        // Stubbed: logic to convert Snake to JSON or storable format
        return ""
    }

    companion object {
        fun newInstance(snakeList: ArrayList<Snake>): ResultsFragment {
            val fragment = ResultsFragment()
            val args = Bundle()
            args.putParcelableArrayList("snakeList", snakeList)
            fragment.arguments = args
            return fragment
        }
    }
}
