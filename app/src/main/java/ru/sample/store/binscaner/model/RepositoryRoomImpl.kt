package ru.sample.store.binscaner.model

import ru.sample.store.binscaner.Bin
import ru.sample.store.binscaner.MyApp
import ru.sample.store.binscaner.model.room.BinEntity

class RepositoryRoomImpl : RepositoryBinInfo, RepositoryBinSave,
    RepositoryBinAvailable {

    override fun getInfo(number: Int, callback: BinCallback) {

        Thread {
            callback.onResponse(
                MyApp.getBinDatabase()
                    .binDao()
                    .getBinByNumber(number).let {
                        if (it.isNotEmpty()){
                            convertHistoryEntityToBin(it).last()
                        } else {
                            Bin("")
                        }
                    }
            )
        }.start()
    }

    override fun addBin(bin: Bin) {
        Thread {
            MyApp.getBinDatabase().binDao().insertRoom(convertBinToEntity(bin))
        }.start()
    }

    override fun getBinAll(callback: CommonListBinCallback) {

        Thread {
            callback.onResponse(
                convertHistoryEntityToBin(
                    MyApp.getBinDatabase().binDao().getBinAll()
                )
            )
        }.start()
    }

    private fun convertHistoryEntityToBin(entityList: List<BinEntity>): List<Bin> {
        return entityList.map {
            Bin(it.name)
        }
    }

    private fun convertBinToEntity(bin: Bin): BinEntity {
        return BinEntity(
            0,
            bin.number
        )
    }
}