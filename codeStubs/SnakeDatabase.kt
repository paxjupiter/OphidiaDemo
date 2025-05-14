package com.cryptid.ophidia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database setup for storing and retrieving snake data.
 * Includes pre-population and utility list generation.
 */
@Database(entities = [Snake::class], version = 3, exportSchema = false)
abstract class SnakeDatabase : RoomDatabase() {

    abstract fun snakeDao(): SnakeDao

    companion object {
        @Volatile
        private var INSTANCE: SnakeDatabase? = null

        // Public lists used for spinner filters in the UI
        var countriesList: List<String> = listOf()
        var colorsList: List<String> = listOf()
        var patternsList: List<String> = listOf()
        var ventralColorsList: List<String> = listOf()
        var ventralPatternsList: List<String> = listOf()
        var allImageUrlsList: List<String> = listOf()

        /**
         * Returns the Room database instance.
         * Performs pre-population and cache list generation.
         */
        fun getDatabase(context: Context): SnakeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SnakeDatabase::class.java,
                    "snake_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(/* database init callback stubbed */)
                    .build()
                    .also { INSTANCE = it }
            }
        }

        // Stubbed functions — real logic omitted in public repo
        private fun prepopulateDatabase(context: Context) { /* ... */ }
        fun generateListsFromCsv(context: Context) { /* ... */ }
        private fun parseCsv(context: Context): List<Snake> = listOf()
    }
}
