package com.gb.android.repository

import androidx.lifecycle.liveData
import com.gb.android.repository.local.HistoryDao
import com.gb.android.repository.remote.RemoteDataSource
import com.gb.android.model.DataModel
import com.gb.android.model.HistoryEntity
import com.gb.android.model.Result
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val historyDao: HistoryDao
) {

    fun search(wordToSearch: String) = liveData(Dispatchers.IO) {
        emit(Result.loading(null))
        val result = remoteDataSource.searchWord(wordToSearch)
        if (result.status == Result.Status.SUCCESS) {
            result.data?.let { it ->
                historyDao.insertAll(convertDataModelSuccessToEntity(it))
            }
        }
        emit(result)
    }

    fun wordCached(wordToSearch: String) = liveData(Dispatchers.IO) {
        emit(Result.success(listOf()))
        val result = historyDao.getDataByWord(wordToSearch)
        if (!result.isNullOrEmpty()) {
            emit(Result.success(result))
        }
    }

    private fun convertDataModelSuccessToEntity(result: List<DataModel>): List<HistoryEntity> {
        val listHistory = mutableListOf<HistoryEntity>()
        if (result.isNotEmpty()) {
            for (entity in result) {
                entity.text?.let { HistoryEntity(it, null) }?.let { listHistory.add(it) }
            }
        }
        return listHistory
    }

}