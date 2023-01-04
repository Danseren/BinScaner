package ru.sample.store.binscaner.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BinEntity::class), version = 1)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao():BinDAO
}