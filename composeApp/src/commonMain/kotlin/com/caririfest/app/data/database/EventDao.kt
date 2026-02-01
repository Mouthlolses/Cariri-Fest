package com.caririfest.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caririfest.app.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getById(id: Int): Flow<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questions: List<Event>)

    @Update
    suspend fun update(question: Event)

    @Delete
    suspend fun delete(question: Event)

    @Query(" SELECT * FROM events  WHERE id = :categoryId GROUP BY date ORDER BY RANDOM() LIMIT 10")
    fun getRandomQuestions(categoryId: String): Flow<List<Event>>

}