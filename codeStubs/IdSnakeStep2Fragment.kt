package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton

/**
 * Second step in the ID flow.
 * Captures binary user input about head color distinctiveness and nape markings.
 * Uses color-coded buttons for interaction clarity.
 */
class IdSnakeStep2Fragment : Fragment() {

    private lateinit var headColorButtons: List<MaterialButton>
    private lateinit var napeButtons: List<MaterialButton>
    private var selectedHeadColorButton: MaterialButton? = null
    private var selectedNapeButton: MaterialButton? = null

    private val viewModel: IdSnakeViewModel by lazy {
        ViewModelProvider(requireActivity())[IdSnakeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_id_snake_step2, container, false)

        // Group and style buttons
        headColorButtons = listOf(
            view.findViewById(R.id.headColorYes),
            view.findViewById(R.id.headColorNo),
            view.findViewById(R.id.headColorUnknown)
        )

        napeButtons = listOf(
            view.findViewById(R.id.napeYes),
            view.findViewById(R.id.napeNo),
            view.findViewById(R.id.napeUnknown)
        )

        // Set click listeners and UI feedback
        setupSelectionButtons(headColorButtons, "HeadColor") { selection, button ->
            viewModel.headColorDistinct = selection
            selectedHeadColorButton = button
        }

        setupSelectionButtons(napeButtons, "Nape") { selection, button ->
            viewModel.napeDistinct = selection
            selectedNapeButton = button
        }

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            (parentFragment as? IdSnakeFragment)?.navigateToStep(2)
        }

        return view
    }

    /**
     * Handles button state styling and ViewModel value updates.
     * Applies tint overrides and selection resets.
     */
    private fun setupSelectionButtons(
        buttons: List<MaterialButton>,
        category: String,
        onSelect: (String, MaterialButton) -> Unit
    ) {
        buttons.forEach { button ->
            // Button click logic (style update, state reset, ViewModel sync)
            // Omitted in demo stub
        }
    }
}
