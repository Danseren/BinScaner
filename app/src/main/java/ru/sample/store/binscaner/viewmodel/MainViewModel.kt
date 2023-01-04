package ru.sample.store.binscaner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sample.store.binscaner.Bin
import ru.sample.store.binscaner.model.CommonListBinCallback
import ru.sample.store.binscaner.model.RepositoryBinAvailable
import ru.sample.store.binscaner.model.RepositoryRoomImpl
import java.io.IOException

class MainViewModel (private val liveData: MutableLiveData<MainFragmentAppState> = MutableLiveData<MainFragmentAppState>()) :
    ViewModel() {

    lateinit var repository: RepositoryBinAvailable

    fun getLiveData(): MutableLiveData<MainFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = RepositoryRoomImpl()
    }


    fun getAllHistory() {
        liveData.value = MainFragmentAppState.Loading
        repository.getBinAll(callback)
    }

    private val callback = object : CommonListBinCallback {
        override fun onResponse(listBin: List<Bin>) {
            liveData.postValue(MainFragmentAppState.SuccessMulti(listBin))
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(MainFragmentAppState.Error(e))
        }
    }

}