package com.caririfest.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.caririfest.app.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [Event::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

}

internal const val dbFileName = "caririfest.db"

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getDatabaseBuilder(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
//        .addMigrations(MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(
            dropAllTables = true
        )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}