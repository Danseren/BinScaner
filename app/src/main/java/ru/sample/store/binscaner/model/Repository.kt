package ru.sample.store.binscaner.model

import ru.sample.store.binscaner.Bin
import ru.sample.store.binscaner.model.dto.BinDTO
import java.io.IOException

fun interface RepositoryInfo {
    fun getInfo(number: Int, callback: MainCallback)
}

interface MainCallback {
    fun onResponse(binDTO: BinDTO)
    fun onFailure(e: IOException)
}

fun interface RepositoryBinInfo {
    fun getInfo(number: Int, callback: BinCallback)
}

interface BinCallback {
    fun onResponse(bin: Bin)
    fun onFailure(e: IOException)
}

fun interface RepositoryBinSave {
    fun addBin(bin: Bin)
}

fun interface RepositoryBinAvailable {
    fun getBinAll(callback: CommonListBinCallback)
}

interface CommonListBinCallback {
    fun onResponse(bin: List<Bin>)
    fun onFailure(e: IOException)
}