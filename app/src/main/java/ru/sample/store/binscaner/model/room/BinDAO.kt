package ru.sample.store.binscaner.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoom(binEntity: BinEntity)

    @Query("INSERT INTO bin_entity_table (name) VALUES(:name)")
    fun insertNative1(name:String)

    @Query("INSERT INTO bin_entity_table (id,name) VALUES(:id,:name)")
    fun insertNative2(id:Long,name:String)

    @Query("SELECT * FROM bin_entity_table WHERE name=:mName")
    fun getBinByNumber(mName:Int):List<BinEntity>

    @Query("SELECT * FROM bin_entity_table")
    fun getBinAll():List<BinEntity>
}