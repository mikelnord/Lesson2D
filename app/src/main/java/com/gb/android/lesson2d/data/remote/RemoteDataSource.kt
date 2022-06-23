package com.gb.android.lesson2d.data.remote

import com.gb.android.lesson2d.model.DataModel
import com.gb.android.lesson2d.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import com.gb.android.lesson2d.model.Result
import com.gb.android.lesson2d.network.services.ApiService

class RemoteDataSource (private val retrofit: Retrofit) {

    suspend fun searchWord(wordToSearch: String): Result<List<DataModel>> {
        val movieService = retrofit.create(ApiService::class.java)
        return getResponse(
            request = { movieService.search(wordToSearch) },
            defaultErrorMessage = "Error fetching Movie Description"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
             val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }

}