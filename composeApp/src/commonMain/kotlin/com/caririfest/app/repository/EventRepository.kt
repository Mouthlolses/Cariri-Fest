package com.caririfest.app.repository

import com.caririfest.app.data.EventService
import com.caririfest.app.data.database.EventDao
import com.caririfest.app.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getAll(): List<Event>
    suspend fun getAllLocal(): Flow<List<Event>>
    suspend fun saveLocal(questions: List<Event>)
    fun getRandomQuestions(categoryId: String): Flow<List<Event>>
}

class EventRepositoryImpl(
    private val service: EventService,
    private val dao: EventDao
) : EventRepository {

    override suspend fun getAll(): List<Event> {
        val events = service.getAll().map { it }
        return events
    }

    override suspend fun getAllLocal(): Flow<List<Event>> {
        return dao.getAll()
    }

    override suspend fun saveLocal(questions: List<Event>) {
        dao.insert(questions)
    }

    override fun getRandomQuestions(categoryId: String): Flow<List<Event>> {
        return dao.getRandomQuestions(categoryId)
    }
}