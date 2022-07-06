package com.gb.android.repository.remote

import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.gb.android.model.DataModel
import com.gb.android.model.Result
import com.gb.android.repository.services.ApiService
import com.gb.android.utils.ErrorUtils

class RemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

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