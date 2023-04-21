package com.nttdata.demoanvil.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Search") val Search: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("Title") val Title: String,
    @SerializedName("Year") val Year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val Type: String,
    @SerializedName("Poster") val Poster: String,
    )


