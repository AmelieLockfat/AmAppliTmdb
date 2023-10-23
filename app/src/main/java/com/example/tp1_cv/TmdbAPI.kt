package com.example.tp1_cv

import com.google.protobuf.Api
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSerieResult

    @GET("trending/person/week")
    suspend fun lastactors(@Query("api_key") api_key: String): TmdbPersonResult

    @GET ("movie/{id}")
     suspend fun detailmovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): TmdbDetailFilm

    @GET ("tv/{id}")
    suspend fun detailserie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): TmdbSerieDetail

    @GET ("person/{id}")
    suspend fun detailperson(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String = "credits"
    ): TmdbDetailPerson


    @GET("search/movie")
    suspend fun searchmovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbMovieResult


    @GET("search/tv")
    suspend fun searchseries(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbSerieResult


    @GET("search/person")
    suspend fun searchpeople(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): TmdbPersonResult
}


