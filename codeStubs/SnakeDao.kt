package com.cryptid.ophidia.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data access object for Snake entries.
 * Provides a clean abstraction for database operations.
 */
@Dao
interface SnakeDao {

    /**
     * Inserts a list of snake objects into the local database.
     * Replaces existing entries on conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(snakes: List<Snake>)

    /**
     * Retrieves all snake entries from the local database.
     */
    @Query("SELECT * FROM Snake")
    suspend fun getAll(): List<Snake>

    /**
     * Retrieves a specific snake by its unique ID.
     */
    @Query("SELECT * FROM Snake WHERE id = :snakeId")
    suspend fun getById(snakeId: Int): Snake
}
