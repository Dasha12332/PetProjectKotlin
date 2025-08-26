package com.example.diplomv1.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diplomv1.R
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.ui.theme.goosberryFamily

class HomeActivity(private var navController: NavController) {

    @Composable
    fun FirstPage(modifier: Modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopInfo(modifier = Modifier.fillMaxWidth())
            IconButton(onClick ={/*TODO*/},
                Modifier
                    .height(460.dp)
                    .width(195.dp)
                    .padding(25.dp)){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.cat),
                    contentDescription = "Информация о приложении",
                    //modifier = Modifier.fillMaxSize().padding(20.dp),
                    tint = Color.Unspecified
                )
            }
            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),  // округлая кнопка
                modifier = Modifier
                    .height(70.dp)
                    .width(180.dp)
                    .padding(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0xFF91D6F8)
                )

            ) {
                Text(
                    "Button 2",
                    fontSize = 28.sp,
                    fontFamily = goosberryFamily,

                    )
            }
            NavigationLine(navController)
        }
    }

}