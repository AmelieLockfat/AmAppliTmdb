package com.example.tp1_cv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScreenDetailActeur(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavController,
) {
    val personDetail by viewModel.acteurDetail.collectAsState()
    if (id != null) {
        viewModel.getDetailsActeur(id)
        personDetail?.let {

            Column(
                modifier = Modifier
                    .background(Color(0xFFbdacd1))
                    .fillMaxSize()
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
                if (it.profile_path != null) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w780" + it.profile_path,
                        modifier = Modifier.size(350.dp),
                        //defaultMinSize(minHeight = 420.dp),
                        contentDescription = "filmiddetail",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    "Naissance : ",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("Le " + ladateenString(it.birthday) + " à " + it.place_of_birth)

                if (it.deathday != null) {
                    Text(
                        "Date de mort : ",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("" + ladateenString(it.deathday))
                }
                Text(
                    "Biographie :",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("" + it.biography)


            }


        }
    }

}




