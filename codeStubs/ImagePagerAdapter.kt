package com.cryptid.ophidia.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying a list of snake images in a ViewPager.
 * Loads local assets with fallback support and smooth swipe handling.
 */
class ImagePagerAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_pager, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val context = holder.imageView.context
        val assetPath = imageUrls[position].trim()

        // Loads image from local assets
        // Falls back to placeholder if image is missing or unreadable
        loadAssetImageOrFallback(context, holder.imageView, assetPath)
    }

    override fun getItemCount() = imageUrls.size

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imagePagerItem)
    }

    private fun loadAssetImageOrFallback(context: android.content.Context, imageView: ImageView, assetPath: String) {
        // Actual image load logic omitted in demo repo
        // Typically involves opening asset stream and handling exception fallback
    }
}
