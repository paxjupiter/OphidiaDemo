package com.cryptid.ophidia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cryptid.ophidia.data.Snake
import com.cryptid.ophidia.ui.adapters.ImagePagerAdapter

/**
 * Displays detailed information about a selected snake species.
 * Includes local images, styled metadata, and inline safety indicators.
 */
class SnakeDetailFragment : Fragment() {

    private lateinit var imageViewPager: ViewPager2
    private lateinit var speciesName: TextView
    private lateinit var commonName: TextView
    private lateinit var descriptionLayman: TextView
    private lateinit var country: TextView
    private lateinit var pattern: TextView
    private lateinit var attributionText: TextView

    private var snake: Snake? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_snake_detail, container, false)

        // Bind views
        imageViewPager = view.findViewById(R.id.imageViewPager)
        speciesName = view.findViewById(R.id.speciesName)
        commonName = view.findViewById(R.id.commonName)
        descriptionLayman = view.findViewById(R.id.descriptionLayman)
        country = view.findViewById(R.id.country)
        pattern = view.findViewById(R.id.pattern)
        attributionText = view.findViewById(R.id.imageAttribution)

        // Load snake details from arguments
        snake = arguments?.getParcelable("snake")
        loadSnakeDetails()

        return view
    }

    private fun loadSnakeDetails() {
        snake?.let { snek ->
            speciesName.text = snek.species_name
            commonName.text = snek.common_name ?: ""
            descriptionLayman.text = snek.description_layman ?: "No description available."
            country.text = snek.country ?: "Unknown"
            pattern.text = snek.pattern ?: "Unknown"

            // Dynamically load local images into ViewPager
            val imagePaths = snek.direct_image_path?.split(",")?.map { it.trim() } ?: emptyList()
            val attributions = snek.attribution?.split(",")?.map { it.trim() } ?: emptyList()

            if (imagePaths.isNotEmpty()) {
                imageViewPager.adapter = ImagePagerAdapter(imagePaths)
                imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        attributionText.text = attributions.getOrNull(position) ?: ""
                    }
                })
                attributionText.text = attributions.firstOrNull() ?: ""
            } else {
                imageViewPager.visibility = View.GONE
                attributionText.text = ""
            }

            // Venomous species may trigger special UI flags (omitted here)
        }
    }

    companion object {
        fun newInstance(snake: Snake): SnakeDetailFragment {
            val fragment = SnakeDetailFragment()
            val args = Bundle()
            args.putParcelable("snake", snake)
            fragment.arguments = args
            return fragment
        }
    }
}
