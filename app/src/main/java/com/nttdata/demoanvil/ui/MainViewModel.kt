package com.nttdata.demoanvil.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nttdata.demoanvil.di.ContributesViewModel
import com.nttdata.demoanvil.domain.GetMovies
import com.nttdata.demoanvil.domain.MovieBusiness
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@ContributesViewModel(MainViewModel::class)
class MainViewModel @Inject constructor(
    private val getMovies: GetMovies
): ViewModel() {

    private lateinit var movies: List<MovieBusiness>

    private var _movie = MutableLiveData<MovieBusiness>()
    val movie: LiveData<MovieBusiness> get() = _movie

    private var index by Delegates.notNull<Int>()

    private var page = 1

    init {
        getMovies()
    }

    fun nextMovie(){
        if(::movies.isInitialized){
            index++
            if(index < movies.size)
                _movie.postValue(movies[index])
            else{
                page++
                getMovies()
            }

        }
    }

    private fun getMovies(){
        viewModelScope.launch {
            movies = getMovies.run(page)
            nextMovie()
        }

        index = -1
    }
}