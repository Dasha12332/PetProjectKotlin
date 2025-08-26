package com.example.diplomv1.com.example.diplomv1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.diplomv1.R
import com.example.diplomv1.Routes

@Composable
fun NavigationLine(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth().background(color = Color(0xFF91D6F8)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
        ){
        IconButton(onClick ={navController.navigate(Routes.Settings.route)}){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.settings_image),
                contentDescription = "Информация о приложении",
                modifier = Modifier.size(55.dp),
                tint = Color.Black
            )
        }
        IconButton(onClick ={navController.navigate(Routes.Home.route)}){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.home_image),
                contentDescription = "Информация о приложении",
                modifier = Modifier.size(60.dp).padding(5.dp),
                tint = Color.Black
            )
        }
        IconButton(onClick ={navController.navigate(Routes.ChooseTopic.route)}){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.levelimage),
                contentDescription = "Информация о приложении",
                modifier = Modifier.size(60.dp).padding(5.dp),
                tint = Color.Black
            )
        }

    }
}


