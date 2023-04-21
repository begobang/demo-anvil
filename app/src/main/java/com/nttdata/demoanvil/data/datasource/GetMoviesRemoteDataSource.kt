package com.nttdata.demoanvil.data.datasource

import com.nttdata.demoanvil.data.MovieResponse
import com.nttdata.demoanvil.data.services.GetMoviesApiService
import retrofit2.await
import javax.inject.Inject
import javax.inject.Named

class GetMoviesRemoteDataSource @Inject constructor(
    @Named("getMoviesApiService") val moviesApiService: GetMoviesApiService
) {

    suspend fun getMovies(page: Int = 1): List<MovieResponse> {
        val response = moviesApiService.getMovies(page = page).await()
        return response.Search
    }
}