package com.gb.android.lesson2d.data

import androidx.lifecycle.liveData
import com.gb.android.lesson2d.data.remote.RemoteDataSource
import com.gb.android.lesson2d.model.Result
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun search(wordToSearch: String)= liveData(Dispatchers.IO) {
        emit(Result.loading(null))
        emit(remoteDataSource.searchWord(wordToSearch))
    }

}