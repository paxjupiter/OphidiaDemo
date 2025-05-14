package com.cryptid.ophidia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cryptid.ophidia.R
import com.cryptid.ophidia.data.Snake

/**
 * Custom adapter for displaying a list of snakes in a RecyclerView.
 * Supports image loading from assets and optional "Add" button behavior.
 */
class SnakeAdapter(
    private val snakes: List<Snake>,
    private val onClick: (Snake) -> Unit,
    private val showAddButton: Boolean = false,
    private val onAddSnake: ((Snake) -> Unit)? = null
) : RecyclerView.Adapter<SnakeAdapter.SnakeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnakeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_snake, parent, false)
        return SnakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SnakeViewHolder, position: Int) {
        val snake = snakes[position]
        holder.bind(snake, onClick, showAddButton, onAddSnake)
    }

    override fun getItemCount() = snakes.size

    class SnakeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.snakeImageView)
        private val speciesName: TextView = view.findViewById(R.id.snakeSpeciesName)
        private val commonName: TextView = view.findViewById(R.id.snakeCommonName)
        private val addButton: TextView = view.findViewById(R.id.addButton)

        fun bind(
            snake: Snake,
            onClick: (Snake) -> Unit,
            showAddButton: Boolean,
            onAddSnake: ((Snake) -> Unit)?
        ) {
            speciesName.text = snake.speciesName
            commonName.text = snake.commonName ?: ""

            // Image loading (from assets or fallback) omitted in public demo
            loadSnakeImage(snake)

            itemView.setOnClickListener { onClick(snake) }

            if (showAddButton) {
                addButton.visibility = View.VISIBLE
                addButton.setOnClickListener { onAddSnake?.invoke(snake) }
            } else {
                addButton.visibility = View.GONE
            }
        }

        private fun loadSnakeImage(snake: Snake) {
            // Loads image from asset path or uses a placeholder
            // Real logic omitted in demo stub
        }
    }
}
