package com.nttdata.demoanvil.data.services

import com.nttdata.demoanvil.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMoviesApiService {
    @GET("/")
    fun getMovies(
        @Query("s") s: String = "star wars",
        @Query("page") page: Int = 1
    ): Call<ApiResponse>
}