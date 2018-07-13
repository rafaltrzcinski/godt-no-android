package com.rafaltrzcinski.godtnoandroid.viewmodel

import android.arch.lifecycle.*
import com.rafaltrzcinski.godtnoandroid.data.CacheRepository
import com.rafaltrzcinski.godtnoandroid.data.RemoteRepository
import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe


class RecipeListViewModel : ViewModel() {

    private val cacheRepository = CacheRepository()
    private val remoteRepository = RemoteRepository()

    fun getRecipesFromCache() = cacheRepository.getLiveRecipes()

    fun cacheRecipes(items: List<Recipe> = emptyList()) = cacheRepository.saveToCache(items)

    fun getRemoteRecipes() = remoteRepository.getRecipes()

    fun getCachedRecipes() = cacheRepository.getCachedRecipes()
}