package com.cryptid.ophidia.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data model representing a snake species.
 * Used for both database storage and in-memory processing.
 */
@Entity
data class Snake(
    @PrimaryKey val id: Int,
    val speciesName: String,
    val commonName: String?,
    val venomStatus: String?,
    val colors: String?,             // e.g., "brown, black"
    val headColorDistinct: String?, // parsed from CSV metadata
    val scaleType: String?,
    val country: String?,
    val imageFile: String?,         // filename reference to asset
    val imagePaths: String?,        // comma-separated local paths
    val attribution: String?,       // string credit for each image
    val napeDescription: String?,
    val patternDescription: String?,
    val ventralColor: String?,
    val ventralPattern: String?,
    val maxLengthCm: Float?,
    val maxLengthIn: Float?,
    val laymanDescription: String?
) : Parcelable {
    // Parcelable logic redacted
    // Enables passing Snake objects between Android components
}
