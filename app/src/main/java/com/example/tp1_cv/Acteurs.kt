package com.example.tp1_cv

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController


@Composable
fun Acteurs (vm: ViewModel) {

}

@Composable
fun ScreenActors(viewModel: MainViewModel, navController : NavController,persons : List<TmdbPerson>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 158.dp),
        modifier = Modifier.background(color = Color(0xFFbdacd1))
    ) { items(persons.size) { index ->
        CardActeur(persons[index], navController)
    }
    }
}



@Composable
fun CardActeur(tmdbPerson: TmdbPerson, navController: NavController) {

    GeneralCard(
        route = "peopleDetail/" + tmdbPerson.id,
        imgPath = tmdbPerson.profile_path,
        firstText = tmdbPerson.name,
        secondText = "",
        navController = navController
    )
}