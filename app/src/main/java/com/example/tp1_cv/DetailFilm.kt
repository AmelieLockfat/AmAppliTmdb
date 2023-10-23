package com.example.tp1_cv

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScreenDetailFilm(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavController
) {
    val movieDetails by viewModel.filmDetail.collectAsState()
    if (id != null) {
        viewModel.getDetailsFilm(id)
        movieDetails?.let {
            Column(
                modifier = Modifier
                    .background(Color(0xFFbdacd1))
                    .verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(it.title, fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                if (it.poster_path != null) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w780" + it.poster_path,
                        modifier = Modifier.size(350.dp),
                        //defaultMinSize(minHeight = 420.dp),
                        contentDescription = "seripiddetail",
                        alignment = Alignment.Center
                    )
                }

                Text("Synopsis : ", color = Color.White
                    , fontSize = 25.sp, fontWeight = Bold )
                Text(it.overview)
                Text("Date de sortie : ", color = Color.White
                    , fontSize = 25.sp, fontWeight = Bold )
                        Text(ladateenString(it.release_date) )


                ListeGenresFilm(genres = it.genres)
          Row() {Text("Vote : ", color = Color.White
              , fontSize = 25.sp, fontWeight = Bold )
              Text(""+it.vote_average, fontSize = 25.sp)
              Text(" / 10", color = Color.White
              , fontSize = 25.sp, fontWeight = Bold )}

             Casting(it.credits.cast, navController)

            }

        }
    }
}

@Composable
fun ListeGenresFilm(genres: List<Genre>?) {
    if (genres != null) {
       Text(text="Genres : ", color = Color.White
           , fontSize = 25.sp, fontWeight = Bold )

        for (genre in genres) {
            Text(genre.name)
        }
    }
}
@Composable
fun Casting(casting: List<Cast>? ,navController : NavController) {
    if (casting != null) {
for (c in casting){
    Text(""+c.name)
}

    }
}