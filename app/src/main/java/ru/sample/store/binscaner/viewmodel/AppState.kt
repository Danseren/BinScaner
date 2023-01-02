package ru.sample.store.binscaner.viewmodel

import ru.sample.store.binscaner.model.dto.BinDTO

sealed class AppState {
    data class Success(val binData: BinDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
