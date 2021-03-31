package fr.train.core

fun <T> Pair<T, T>.containsOnly(vararg arr: T) = arr.contains(first) && arr.contains(second)
