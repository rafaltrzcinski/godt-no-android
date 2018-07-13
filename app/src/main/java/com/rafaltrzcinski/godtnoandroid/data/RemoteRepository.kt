package com.rafaltrzcinski.godtnoandroid.data

import android.arch.lifecycle.MutableLiveData
import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe
import com.rafaltrzcinski.godtnoandroid.remote.RemoteClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class RemoteRepository {

    private val remote = RemoteClient.getRemoteService()
    private val liveData = MutableLiveData<List<Recipe>>()


    fun getRecipes(): MutableLiveData<List<Recipe>> {
        remote.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5, TimeUnit.SECONDS)
                .subscribe(
                        { liveData.value = it },
                        { liveData.postValue(null) }
                )
        return liveData
    }
}