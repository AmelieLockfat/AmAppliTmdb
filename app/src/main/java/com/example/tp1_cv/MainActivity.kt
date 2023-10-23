package com.example.tp1_cv

import android.graphics.drawable.Icon
import android.os.Bundle


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.navigation.compose.currentBackStackEntryAsState
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tp1_cv.ui.theme.TP1_CVTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val viewModel: MainViewModel by viewModels()
            val backStackEntry by navController.currentBackStackEntryAsState() //j
            val currentRoute = backStackEntry?.destination?.route
            var oncherche = remember { mutableStateOf(false) }
            TP1_CVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                )
                {
                    Scaffold(
                        topBar = {
                            if (currentRoute != "profil_cv") {
                                TopAppBar(
                                    title = {
                                        if (!oncherche.value) {
                                            Text(
                                                text = "Fav'app",
                                                fontFamily = FontFamily.Cursive,
                                                fontSize = 40.sp
                                            )
                                        } else {
                                            Recherche(navController = navController)
                                        }
                                    },
                                    actions = {
                                        IconButton(
                                            onClick = {
                                                oncherche.value = !oncherche.value
                                            }
                                        ) {
                                            Icon(
                                                painterResource(id = R.drawable.loupe),
                                                contentDescription = "logo recherche"
                                            )
                                        }
                                    }
                                )
                            }
                        },
                        bottomBar = {
                            if (currentRoute != "profil_cv") {
                                NavigationBar(containerColor = Color(0xFFbdacd1)) {
                                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                                    val currentDestination = navBackStackEntry?.destination
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.clap),
                                                contentDescription = "logo films",
                                                modifier = Modifier.size(50.dp)

                                            )
                                        },
                                        label = { },
                                        selected = false,
                                        onClick = {
                                            navController.navigate("Films")
                                        })
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.video),
                                                contentDescription = "logo series",
                                                modifier = Modifier.size(50.dp)
                                            )
                                        },
                                        label = { },
                                        selected = false,

                                        onClick = {

                                            navController.navigate("Séries")
                                        })
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                painterResource(id = R.drawable.acteur),
                                                contentDescription = "logo acteur",
                                                modifier = Modifier.size(50.dp)
                                            )
                                        },
                                        label = { },
                                        selected = false,

                                        onClick = {
                                            navController.navigate("Acteurs")
                                        })
                                }
                            }
                        })
                    { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "profil_cv",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("profil_cv") {
                                ScreenProfil(
                                    navController = navController,
                                    windowClass = windowClass
                                )
                            }
                            composable("Films") {
                                val movies by viewModel.movies.collectAsState()
                                ScreenFilm(

                                    viewModel = viewModel,
                                    navController = navController,
                                    movies = movies
                                )
                            }
                            composable("Séries") {
                                val series by viewModel.series.collectAsState()
                                ScreenSeries(
                                    viewModel = viewModel,
                                    navController = navController,
                                    series = series
                                )
                            }
                            composable("Acteurs") {
                                val persons by viewModel.persons.collectAsState()
                                ScreenActors(
                                    viewModel = viewModel,
                                    navController = navController, persons
                                )
                            }
                            composable(
                                "filmDetail/{idMovie}",
                                arguments = listOf(navArgument("idMovie") {
                                    type = NavType.IntType
                                })
                            ) {
                                ScreenDetailFilm(
                                    viewModel,
                                    backStackEntry?.arguments?.getInt("idMovie"),
                                    navController
                                )
                            }
                            composable(
                                "serieDetail/{idSerie}",
                                arguments = listOf(navArgument("idSerie") {
                                    type = NavType.IntType
                                })
                            ) {
                                ScreenSerieDetail(
                                    viewModel,
                                    backStackEntry?.arguments?.getInt("idSerie"),
                                    navController
                                )
                            }
                            composable(
                                "peopleDetail/{idPerson}",
                                arguments = listOf(navArgument("idPerson") {
                                    type = NavType.IntType
                                })
                            ) {
                                ScreenDetailActeur(
                                    viewModel,
                                    backStackEntry?.arguments?.getInt("idPerson"),
                                    navController
                                )
                            }
                            composable(
                                "filmsSearch/{searchTerm}",
                                arguments = listOf(navArgument("searchTerm") {
                                    type = NavType.StringType
                                })
                            ) {
                                backStackEntry?.arguments?.getString("searchTerm")
                                    ?.let { it1 ->
                                        viewModel.getFilmSearch(it1)
                                        val movies2 by viewModel.searchMovies.collectAsState()
                                        ScreenFilm( viewModel, navController, movies2)
                                    }
                            }

                            composable(
                                "seriesSearch/{searchTerm}",
                                arguments = listOf(navArgument("searchTerm") {
                                    type = NavType.StringType
                                })
                            ) {
                                backStackEntry?.arguments?.getString("searchTerm")
                                    ?.let { it1 ->
                                        viewModel.getSeriesSearch(it1)
                                        val series by viewModel.searchSeries.collectAsState()
                                        ScreenSeries(viewModel, navController, series)
                                    }
                            }

                            composable(
                                "peopleSearch/{searchTerm}",
                                arguments = listOf(navArgument("searchTerm") {
                                    type = NavType.StringType
                                })
                            ) {
                                backStackEntry?.arguments?.getString("searchTerm")
                                    ?.let { it1 ->
                                        viewModel.getActeursSearch(it1)
                                        val people by viewModel.searchActeurs.collectAsState()
                                        ScreenActors(viewModel, navController, people)
                                    }
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier) {
        Text(
            text = "$name",
            modifier = modifier
        )
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP1_CVTheme {

    }
}

