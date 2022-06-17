package com.gb.android.lesson2d.network.services

import com.gb.android.lesson2d.model.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
   suspend fun search(@Query("search") wordToSearch: String): Response<List<DataModel>>
}