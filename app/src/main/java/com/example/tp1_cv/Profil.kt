package com.example.tp1_cv

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tp1_cv.ui.theme.TP1_CVTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
 fun ScreenProfil(navController: NavController, windowClass : WindowSizeClass)
            {
                when (windowClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 30.dp)
                        ) {

                            BonhommeNeige()
                            Nom()
                            PresentationAm()
                            Mail()
                            LinkedIn()
                            BoutonDem(navController)
                        }
                    }
                    else -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.padding(start = 40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                BonhommeNeige()
                                Nom()
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                PresentationAm()
                                Mail()
                                LinkedIn()
                                BoutonDem(navController)

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

@Composable
fun Nom() {
    Text(
        text = "Amélie LOCK-FAT",
        fontSize = 40.sp,
        color = Color.Blue,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BonhommeNeige() {
    Image(
        painterResource(id = R.drawable.img_20230225_wa0060),
        contentDescription = "cmwa",
        Modifier
            .size(260.dp)
            .clip(CircleShape)
    )
}

@Composable
fun PresentationAm() {
    Text(
        text = "Etudiante en 4ème année à l'école d'ingénieurs ISIS Castres",
        fontSize = 30.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Mail() {
    Row(Modifier.padding(top = 75.dp)) {
        Image(
            painterResource(id = R.drawable.e_mail),
            contentDescription = "mail",
            Modifier.size(40.dp)
        )
        Text(
            text = "   amelielockfat@gmail.com"
        )
    }

}

@Composable
fun LinkedIn() {
    Row(Modifier.padding(top = 20.dp)) {

        Image(
            painterResource(id = R.drawable.linkedin),
            contentDescription = "linkedin",
            Modifier.size(30.dp)
        )
        Text(
            text = "   www.linkedin.com/in/amelie-lock-fat"
        )
    }
}

@Composable
fun BoutonDem(navController: NavController) {
    Button(onClick = { navController.navigate("Films") }, Modifier.padding(top = 40.dp),  colors = ButtonDefaults.buttonColors(containerColor = Color.Blue,
        contentColor = Color.White)) {

        Text("Démarrer")
    }
}



