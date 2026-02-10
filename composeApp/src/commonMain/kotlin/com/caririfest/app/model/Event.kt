package com.caririfest.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "events")
data class Event(
    @PrimaryKey val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val img: String,
    val link: String,
    val location: String,
    val place: String,
    val time: String,
    val favorite: Boolean,
    val hot: Boolean
)