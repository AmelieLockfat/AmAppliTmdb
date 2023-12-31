package com.example.tp1_cv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun GeneralCard(
    route: String,
    imgPath: String?,
    firstText: String,
    secondText: String?,
    navController: NavController
) {
    Card(
        modifier = Modifier

            .padding(15.dp)
            .clickable { navController.navigate(route)},
    ) {
        Column(modifier = Modifier.background(color = Color(0xFF6050dc))) {
            if (imgPath != null) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$imgPath",
                    contentDescription = firstText
                )
            }
            Text(
                text = firstText,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier
                    .width(180.dp),
            )
            if (secondText != null) {
                Text(
                    text = secondText, color = Color.White
                )
            }

        }
    }
}
