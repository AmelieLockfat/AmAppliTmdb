package com.example.tp1_cv

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController


@Composable
fun Series(vm: ViewModel) {

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenSeries(viewModel: MainViewModel, navController: NavController, series :List<TmdbSerie>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 158.dp),
        modifier = Modifier.background(color = Color(0xFFbdacd1))
    ) { items(series.size) { index ->
        CardSerie(series[index], navController)
    }
    }
}


@Composable
fun CardSerie(tmdbSerie: TmdbSerie, navController: NavController) {
    val date = if (!tmdbSerie.first_air_date.isNullOrBlank()) {
        ladateenString(tmdbSerie.first_air_date)
    } else {
        "date indéfinie"
    }
    GeneralCard(
        route = "serieDetail/" + tmdbSerie.id,
        imgPath = tmdbSerie.poster_path,
        firstText = tmdbSerie.name,
        secondText = date,
        navController = navController
    )
}




