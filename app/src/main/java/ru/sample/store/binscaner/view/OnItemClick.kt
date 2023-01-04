package ru.sample.store.binscaner.view

import ru.sample.store.binscaner.Bin

fun interface OnItemClick {
    fun onItemClick(bin: Bin)
}