package com.wojdor.common.extension

import kotlin.random.Random

fun <T> Random.Default.nextListItem(list: List<T>) = list[nextInt(list.size)]