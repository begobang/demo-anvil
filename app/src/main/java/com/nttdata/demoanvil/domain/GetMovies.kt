package com.nttdata.demoanvil.domain

import com.nttdata.demoanvil.data.repositoryImpl.GetMoviesRepositoryImpl
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val repository: GetMoviesRepositoryImpl
){
    suspend fun run(page: Int = 1): List<MovieBusiness> {
        return repository.getMovies(page)
    }
}