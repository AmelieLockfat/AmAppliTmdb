package com.example.tp1_cv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val service: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
val api = service.create(Api::class.java)
const val api_key = "f7697226700dd6eecbf0f1756090450a"

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSerie>>(listOf())
    val persons = MutableStateFlow<List<TmdbPerson>>(listOf())
    val filmDetail = MutableStateFlow<TmdbDetailFilm?>(null)
    val serieDetail = MutableStateFlow<TmdbSerieDetail?>(null)
    val acteurDetail = MutableStateFlow<TmdbDetailPerson?>(null)
    val searchMovies = MutableStateFlow<List<TmdbMovie>>(listOf())

    init {
        getMovies()
        getSeries()
        getPersons()
    }
    fun getMovies() {
        viewModelScope.launch {
            val res = api.lastmovies(api_key)
            movies.value = res.results
        }}

        fun getSeries() {
            viewModelScope.launch {
                val res = api.lastseries(api_key)
                series.value = res.results
            }
        }
    fun getPersons() {
        viewModelScope.launch {
            val res = api.lastactors(api_key)
            persons.value = res.results
        }
    }

    fun getDetailsFilm(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                filmDetail.value = api.detailmovie(id, api_key, "fr")
            }
    }}

    fun getDetailsSerie(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                serieDetail.value = api.detailserie(id, api_key, "fr")
            }
        }
    }

    fun getDetailsActeur(id : Int) {
        viewModelScope.launch {
            if (id != 0) {
                acteurDetail.value = api.detailperson(id, api_key, "fr")
            }
        }
    }
    fun getFilmSearch(query: String) {
        viewModelScope.launch {
            val res = api.searchmovie(query, api_key, "fr")
            searchMovies.value = res.results
        }
    }
}
