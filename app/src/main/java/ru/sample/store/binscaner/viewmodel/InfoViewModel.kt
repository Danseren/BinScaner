package ru.sample.store.binscaner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sample.store.binscaner.model.MainCallback
import ru.sample.store.binscaner.model.RepositoryInfo
import ru.sample.store.binscaner.model.RepositoryInfoOkHttpImpl
import ru.sample.store.binscaner.model.dto.BinDTO
import java.io.IOException

class InfoViewModel (
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
): ViewModel() {
    lateinit var repository: RepositoryInfo

    fun getLiveData(): MutableLiveData<AppState> {
        repository = RepositoryInfoOkHttpImpl()
        return liveData
    }

    fun getInfo(number: Int) {
        repository = RepositoryInfoOkHttpImpl()
        liveData.value = AppState.Loading
        repository.getInfo(number, callback)
    }

    private val callback = object : MainCallback {
        override fun onResponse(binDTO: BinDTO) {
            liveData.postValue(AppState.Success(binDTO))
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(AppState.Error(e))
        }

    }
}