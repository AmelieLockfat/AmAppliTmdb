package com.example.tp1_cv

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.roundToInt
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@Composable
fun Films(vm : ViewModel) {

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenFilm( viewModel: MainViewModel, navController : NavController,  movies: List<TmdbMovie> ) {


            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 158.dp),
                modifier = Modifier.background(color = Color(0xFFbdacd1))
            ) {
                items(movies.size) { index ->
                    CardMovie(movies[index], navController)
                }
            }
        }


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardMovie(tmdbMovie: TmdbMovie, navController: NavController) {
    val date = if (!tmdbMovie.release_date.isNullOrBlank()) {
        ladateenString(tmdbMovie.release_date)
    } else {
         "date indéfinie"
    }
    GeneralCard(
        route = "filmDetail/" + tmdbMovie.id,
        imgPath = tmdbMovie.poster_path,
        firstText = tmdbMovie.title,
        secondText = date,
        navController = navController
    )
}
@RequiresApi(Build.VERSION_CODES.O)
fun ladateenString(date: String?): String {
    val datedujour = LocalDate.parse(date) // from XXXX-XX-XX to date
    return datedujour.dayOfMonth.toString() + " " + datedujour.month.getDisplayName(
        TextStyle.SHORT,
        Locale.getDefault()
    ) + " " + datedujour.year.toString()
}