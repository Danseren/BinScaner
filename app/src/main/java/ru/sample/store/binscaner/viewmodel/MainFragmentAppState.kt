package ru.sample.store.binscaner.viewmodel

import ru.sample.store.binscaner.Bin

sealed class MainFragmentAppState {
    data class SuccessMulti(val binList: List<Bin>) : MainFragmentAppState()
    data class Error(val error: Throwable) : MainFragmentAppState()
    object Loading : MainFragmentAppState()
}