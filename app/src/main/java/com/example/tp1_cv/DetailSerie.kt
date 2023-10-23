package com.example.tp1_cv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScreenSerieDetail(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavController, windowClass: WindowSizeClass
) {
    val serieDetail by viewModel.serieDetail.collectAsState()
    if (id != null) {
        viewModel.getDetailsSerie(id)

        serieDetail?.let {
            when (windowClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
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
                        Text(
                            it.name,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        if (it.poster_path != null) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w780" + it.poster_path,
                                modifier = Modifier.size(350.dp),
                                //defaultMinSize(minHeight = 420.dp),
                                contentDescription = "filmiddetail",
                                alignment = Alignment.Center
                            )
                        }

                        Text(
                            "Synopsis : ",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(it.overview)
                        Text(
                            "Date de sortie : ",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(ladateenString(it.first_air_date))


                        ListeGenresFilm(genres = it.genres)
                        Row() {
                            Text(
                                "Vote : ",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text("" + it.vote_average, fontSize = 25.sp)
                            Text(
                                " / 10",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Casting(it.credits.cast, navController)
                    }
                }

                else -> {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.background(Color(0xFFbdacd1))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.background(Color(0xFFbdacd1))
                        ) {
                            Text(
                                it.name,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .width(250.dp),
                                textAlign = TextAlign.Center
                            )
                            if (it.poster_path != null) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w780" + it.poster_path,
                                    modifier = Modifier.size(350.dp),
                                    //defaultMinSize(minHeight = 420.dp),
                                    contentDescription = "seripiddetail",
                                    alignment = Alignment.Center
                                )
                            }
                        }
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Text(
                                "Synopsis : ",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(it.overview)
                            Text(
                                "Date de sortie : ",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(ladateenString(it.first_air_date))


                            ListeGenresFilm(genres = it.genres)
                            Row() {
                                Text(
                                    "Vote : ",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("" + it.vote_average, fontSize = 25.sp)
                                Text(
                                    " / 10",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Casting(it.credits.cast, navController)
                        }
                    }
                }

            }
        }
    }
}



