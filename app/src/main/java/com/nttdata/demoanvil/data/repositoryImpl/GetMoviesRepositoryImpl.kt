package com.nttdata.demoanvil.data.repositoryImpl

import com.nttdata.demoanvil.data.datasource.GetMoviesRemoteDataSource
import com.nttdata.demoanvil.data.toDomain
import com.nttdata.demoanvil.domain.MovieBusiness
import javax.inject.Inject

class GetMoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: GetMoviesRemoteDataSource
) {
    suspend fun getMovies(page: Int = 1): List<MovieBusiness> {
        val response = moviesRemoteDataSource.getMovies(page = page)
        return response.map { it.toDomain() }
    }
}