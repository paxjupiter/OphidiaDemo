package com.cryptid.ophidia.ui

import androidx.lifecycle.ViewModel

/**
 * Shared ViewModel used across the ID flow fragments.
 * Holds user input state as they progress through the filter system.
 */
class IdSnakeViewModel : ViewModel() {

    var selectedCountry: String? = null
    var selectedColors: List<String> = emptyList()
    var headColorDistinct: String? = null
    var napeDistinct: String? = null
    var selectedPattern: String? = null
    var selectedVentralColor: String? = null
    var selectedVentralPattern: String? = null
}
