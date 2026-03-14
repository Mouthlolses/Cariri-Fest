package com.caririfest.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.caririfest.app.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id IN (:ids)")
    fun getAllHandle(ids: List<String>): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getById(id: String): Flow<Event?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(events: List<Event>)

    @Query("DELETE FROM events WHERE id IN (:ids)")
    suspend fun deleteEventByIds(ids: List<String>)

    @Query("DELETE FROM events")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(events: List<Event>) {
        deleteAll()
        insert(events)
    }

    @Query("DELETE FROM events WHERE id NOT IN (:ids)")
    suspend fun deleteEventsNotIn(ids: List<String>)

    @Query(
        """
         SELECT * FROM events
         WHERE LOWER(title) LIKE '%' || :query || '%'
         OR LOWER(location) LIKE '%' || :query || '%'
         """
    )
    fun search(query: String): Flow<List<Event>>

}