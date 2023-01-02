package ru.sample.store.binscaner.model

import ru.sample.store.binscaner.model.dto.BinDTO
import java.io.IOException

fun interface RepositoryInfo {
    fun getInfo(number: Int, callback: MainCallback)
}

interface MainCallback {
    fun onResponse(binDTO: BinDTO)
    fun onFailure(e: IOException)
}