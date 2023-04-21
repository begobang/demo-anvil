package com.nttdata.demoanvil.data

import com.nttdata.demoanvil.domain.MovieBusiness


fun MovieResponse.toDomain(): MovieBusiness  = MovieBusiness(Title, Year, imdbID, Type, Poster)