package com.cryptid.ophidia.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Pager adapter for the snake ID flow.
 * Hosts a series of fragments, each representing a step in the identification process.
 */
class IdSnakePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3 // Number of ID steps

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> /* Step 1 Fragment Placeholder */
            1 -> /* Step 2 Fragment Placeholder */
            2 -> /* Step 3 Fragment Placeholder */
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}
