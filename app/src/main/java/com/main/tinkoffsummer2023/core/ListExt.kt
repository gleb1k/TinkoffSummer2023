package com.main.tinkoffsummer2023.core

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

fun <T> PersistentList<T>.replaceByField(fieldSelector: (T) -> Any, replacement: T): PersistentList<T> {
    val index = this.indexOfFirst { fieldSelector(it) == fieldSelector(replacement) }
    return if (index != -1) {
        this.toMutableList().apply { set(index, replacement) }.toPersistentList()
    } else {
        this
    }
}

fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }
