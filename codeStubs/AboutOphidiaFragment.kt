package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Informational fragment that describes the Ophidia app's purpose,
 * and provides external links to related resources.
 */
class AboutOphidiaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout associated with this screen
        return inflater.inflate(R.layout.fragment_about_ophidia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up button listener to launch an external website
        // (Actual logic omitted in demo repo)

        // Example:
        // visitWebsiteButton.setOnClickListener {
        //     openExternalUrl("https://...")
        // }
    }
}
