package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout

/**
 * Final step in the snake ID flow.
 * Captures multi-pattern selections, ventral color choices (via color buttons),
 * and ventral pattern input from a dropdown.
 */
class IdSnakeStep3Fragment : Fragment() {

    private lateinit var ventralPatternSpinner: Spinner
    private lateinit var ventralColorContainer: FlexboxLayout
    private val selectedPatterns = mutableSetOf<String>()

    private val viewModel: IdSnakeViewModel by lazy {
        ViewModelProvider(requireActivity())[IdSnakeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_id_snake_step3, container, false)

        // Spinner setup for ventral patterns
        ventralPatternSpinner = view.findViewById(R.id.ventralPatternSpinner)
        setupSpinners()

        // Dynamically render ventral color buttons in Flexbox layout
        ventralColorContainer = view.findViewById(R.id.ventralColorContainer)
        setupVentralColorButtons()

        // Set up toggle buttons for multi-pattern selection
        val patternButtons = listOf(
            view.findViewById<ImageButton>(R.id.button_uniform),
            view.findViewById<ImageButton>(R.id.button_stripes),
            view.findViewById<ImageButton>(R.id.button_rings_banded),
            view.findViewById<ImageButton>(R.id.button_spotted),
            view.findViewById<ImageButton>(R.id.button_blotches),
            view.findViewById<ImageButton>(R.id.button_anterior_posterior)
        )

        patternButtons.forEach { button ->
            button.setOnClickListener {
                togglePatternSelection("LabelForThisButton", button)
            }
        }

        // Final submission button
        view.findViewById<Button>(R.id.finishButton).setOnClickListener {
            viewModel.selectedPattern = selectedPatterns.joinToString("|")
            viewModel.selectedVentralColor = /* last selected color */
            viewModel.selectedVentralPattern = ventralPatternSpinner.selectedItem?.toString() ?: ""

            (parentFragment as? IdSnakeFragment)?.onUserActionComplete()
        }

        return view
    }

    private fun togglePatternSelection(pattern: String, button: ImageButton) {
        // Updates selectedPatterns set and applies button visual feedback
    }

    private fun setupSpinners() {
        // Retrieves spinner lists from SharedPreferences
        // Removes duplicates and assigns ArrayAdapters
    }

    private fun setupVentralColorButtons() {
        // Renders buttons with solid or gradient backgrounds
        // Applies a prismatic visual order
        // Uses SharedPreferences to load available ventral colors
    }
}
