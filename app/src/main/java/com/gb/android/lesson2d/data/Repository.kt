package com.gb.android.lesson2d.data

import androidx.lifecycle.liveData
import com.gb.android.lesson2d.data.local.HistoryDao
import com.gb.android.lesson2d.data.remote.RemoteDataSource
import com.gb.android.lesson2d.model.DataModel
import com.gb.android.lesson2d.model.HistoryEntity
import com.gb.android.lesson2d.model.Result
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