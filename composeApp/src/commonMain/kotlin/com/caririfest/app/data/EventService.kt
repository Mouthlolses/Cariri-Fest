package com.caririfest.app.data

import com.caririfest.app.model.Event
import com.caririfest.app.network.dto.EventResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json

interface EventService {
    suspend fun getAll(): List<Event>
}

class EventServiceImpl(
    private val httpClient: HttpClient
) : EventService {
    override suspend fun getAll(): List<Event> {
        return runCatching {
            val response: EventResponse =
                httpClient
                    .get("projects/eventapp-d6adb/databases/(default)/documents/event") {

                    }
                    .body()
            response.documents.map {
                Event(
                    id = it.id,
                    title = it.fields.title.stringValue,
                    desc = it.fields.desc.stringValue,
                    date = it.fields.date.stringValue,
                    img = it.fields.img.stringValue,
                    link = it.fields.link.stringValue,
                    location = it.fields.location.stringValue,
                    place = it.fields.place.stringValue,
                    time = it.fields.time.stringValue,
                    favorite = it.fields.favorite.booleanValue,
                    hot = it.fields.hot.booleanValue
                )
            }
        }.getOrElse { e ->
            println("Erro: $e")
            emptyList()
        }
    }
}