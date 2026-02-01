package com.caririfest.app.data

import com.caririfest.app.model.Event
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

interface EventService {
    suspend fun getAll(): List<Event>
}

class EventServiceImpl(
    private val httpClient: HttpClient
) : EventService {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getAll(): List<Event> {
        return runCatching {
            val bodyText = httpClient.get("questions.json").body<String>()
            json.decodeFromString<List<Event>>(bodyText)
        }.getOrElse { e ->
            println("Erro ao processar questions.json: $e")
            emptyList()
        }
    }
}