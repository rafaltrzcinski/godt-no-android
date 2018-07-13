package com.rafaltrzcinski.godtnoandroid.data

import android.arch.lifecycle.MutableLiveData
import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe


class CacheRepository {

    private val cachedItems: MutableList<Recipe> = mutableListOf()
    private val liveDataItems = MutableLiveData<List<Recipe>>()

    fun saveToCache(items: List<Recipe>) {
        cachedItems.addAll(items)
        liveDataItems.value = cachedItems
    }

    fun getLiveRecipes(): MutableLiveData<List<Recipe>> = liveDataItems

    fun getCachedRecipes() = cachedItems
}