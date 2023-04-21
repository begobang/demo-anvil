package com.nttdata.demoanvil.di

import com.nttdata.demoanvil.data.datasource.GetMoviesRemoteDataSource
import com.nttdata.demoanvil.data.repositoryImpl.GetMoviesRepositoryImpl
import com.nttdata.demoanvil.data.services.GetMoviesApiService
import com.nttdata.demoanvil.domain.GetMovies
import com.nttdata.demoanvil.ui.MainViewModel
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named

abstract class AppScope private constructor()

abstract class ViewModelScope private constructor()

/* ----- Components Region ----- */

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {
    fun viewModelComponent(): ViewModelComponent
}

@SingleIn(ViewModelScope::class)
@MergeSubcomponent(ViewModelScope::class)
interface ViewModelComponent {
    @Named("viewModel")
    fun viewModel(): MainViewModel
    @Named("getMovies")
    fun getMovies(): GetMovies
    @Named("getMoviesRepository")
    fun getMoviesRepository(): GetMoviesRepositoryImpl
    @Named("getMoviesRemoteDataSource")
    fun getMoviesRemoteDataSource(): GetMoviesRemoteDataSource
    @Named("getMoviesApiService")
    fun getMoviesApiService(): GetMoviesApiService
    @Named("retrofit")
    fun retrofit(): Retrofit
    @Named("okHttp")
    fun okHttpClient(): OkHttpClient
    @Named("loggingInterceptor")
    fun loggingInterceptor(): HttpLoggingInterceptor

}