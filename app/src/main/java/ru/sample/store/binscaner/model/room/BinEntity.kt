package ru.sample.store.binscaner.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_entity_table")
data class BinEntity(
    @PrimaryKey( autoGenerate = true)
    val id: Long,
    val name: String
)
