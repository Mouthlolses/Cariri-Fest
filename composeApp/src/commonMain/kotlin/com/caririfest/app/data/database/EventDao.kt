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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<Event>)

    @Query("SELECT * FROM events WHERE id IN (:ids)")
    suspend fun getEventById(ids: List<String>): List<Event>

    @Query("DELETE FROM events WHERE id IN (:ids)")
    suspend fun deleteEventByIds(ids: List<String>)

}