package com.wojdor.common.extension

fun <T> MutableCollection<T>.clearAndAddAll(collection: Collection<T>) {
    clear()
    addAll(collection)
}