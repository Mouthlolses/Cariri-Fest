package com.caririfest.app.repository

import com.caririfest.app.data.EventService
import com.caririfest.app.data.database.EventDao
import com.caririfest.app.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

interface EventRepository {
    fun getAll(): Flow<List<Event>>

    fun getById(id: String): Flow<Event?>

    suspend fun refresh()
}

class EventRepositoryImpl(
    private val service: EventService,
    private val dao: EventDao
) : EventRepository {

    override fun getAll(): Flow<List<Event>> =
        dao.getAll()

    override fun getById(id: String): Flow<Event?> = flow {
        refresh()
        emitAll(dao.getById(id))
    }

    override suspend fun refresh() {
        try {
            val remoteEvents = service.getAll()

            if (remoteEvents.isNotEmpty()) {
                dao.insert(remoteEvents)
            }

        } catch (e: Exception) {
            println("Erro ao processar questions.json: $e")
            throw e
        }
    }
}