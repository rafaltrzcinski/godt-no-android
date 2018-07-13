package com.rafaltrzcinski.godtnoandroid.remote

import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe
import io.reactivex.Single
import retrofit2.http.GET


interface RemoteService {

    @GET("getRecipesListDetailed?tags=&size=thumbnailmedium&ratio=1&limit=50&from=0")
    fun getRecipes(): Single<List<Recipe>>
}