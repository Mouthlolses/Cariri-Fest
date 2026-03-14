package com.caririfest.app.repository

import com.caririfest.app.data.EventService
import com.caririfest.app.data.database.EventDao
import com.caririfest.app.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getAll(): Flow<List<Event>>

    fun getAllHandle(ids: List<String>): Flow<List<Event>>

    fun getById(id: String): Flow<Event?>

    fun search(query: String): Flow<List<Event>>

    suspend fun refresh()
}

class EventRepositoryImpl(
    private val service: EventService,
    private val dao: EventDao
) : EventRepository {

    override fun getAll(): Flow<List<Event>> =
        dao.getAll()


    override fun getAllHandle(ids: List<String>): Flow<List<Event>> =
        dao.getAllHandle(ids)


    override fun getById(id: String): Flow<Event?> =
        dao.getById(id)

    override fun search(query: String): Flow<List<Event>> =
        dao.search(query)

    override suspend fun refresh() {

        val remoteEvents = service.getAll()

        if (remoteEvents.isNotEmpty()) {
            dao.replaceAll(remoteEvents)
        }
    }
}