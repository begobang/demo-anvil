package com.nttdata.demoanvil.di

import com.nttdata.demoanvil.data.QueryInterceptor
import com.nttdata.demoanvil.data.datasource.GetMoviesRemoteDataSource
import com.nttdata.demoanvil.data.repositoryImpl.GetMoviesRepositoryImpl
import com.nttdata.demoanvil.data.services.GetMoviesApiService
import com.nttdata.demoanvil.domain.GetMovies
import com.nttdata.demoanvil.ui.MainViewModel
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@ContributesTo(AppScope::class)
object ModuleDI {

  @Provides
  @Named("viewModel")
  fun provideViewModel(repository: GetMovies): MainViewModel = MainViewModel(repository)

  @Provides
  @Named("getMovies")
  fun provideGetMovies(repository: GetMoviesRepositoryImpl): GetMovies {
    return GetMovies(repository)
  }

  @Provides
  @Named("getMoviesRepository")
  fun provideGetMoviesRepository(remoteDataSource: GetMoviesRemoteDataSource): GetMoviesRepositoryImpl {
    return GetMoviesRepositoryImpl(remoteDataSource)
  }

  @Provides
  @Named("getMoviesRemoteDataSource")
  fun provideGetMoviesRemoteDataSource(
    @Named("getMoviesApiService") getMoviesApiService: GetMoviesApiService
  ): GetMoviesRemoteDataSource {
    return GetMoviesRemoteDataSource(getMoviesApiService)
  }

  @Provides
  @Named("loggingInterceptor")
  fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  @Provides
  @Named("okHttp")
  fun provideOkHttpClient(
    @Named("loggingInterceptor") loggingInterceptor: HttpLoggingInterceptor,
    queryInterceptor: QueryInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(queryInterceptor)
      .build()
  }

  @Provides
  @Named("getMoviesApiService")
  fun provideGetMoviesApiService(
    @Named("retrofit") retrofit: Retrofit
  ): GetMoviesApiService {
    return createRetrofitImplementation(retrofit)
  }

  @Provides
  @Named("retrofit")
  fun provideRetrofit(
    @Named("okHttp") okHttpClient: OkHttpClient
  ): Retrofit {
    return createRetrofit(okHttpClient)
  }

  private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl("https://www.omdbapi.com")
      .client(okHttpClient)
      .addConverterFactory(createConverterFactory())
      .build()

  private fun createConverterFactory(vararg jsonAdapters: JsonAdapter<Any>): Converter.Factory {
    return MoshiConverterFactory.create(
      Moshi.Builder().run {
        jsonAdapters.forEach { add(it) }
        build()
      }
    )
  }


  private inline
  fun <reified T> createRetrofitImplementation(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
  }


}
