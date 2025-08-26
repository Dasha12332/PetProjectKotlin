package com.example.diplomv1.View

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.R
import com.example.diplomv1.Routes
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.ViewModel.ChooseRightViewModel
import com.example.diplomv1.ViewModel.DataBaseViewModel
import com.example.diplomv1.ViewModel.DataBaseViewModelFactory
import com.example.diplomv1.ui.theme.goosberryFamily

class ChooseTopicActivity(private var navController: NavController) {

    @Composable
    fun ChooseTopic() {
        val context = LocalContext.current
        val viewModel: DataBaseViewModel = viewModel(
            factory = DataBaseViewModelFactory(context.applicationContext as Application)
        )
        val section = viewModel.sectionList

        Column(Modifier.background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopInfo(modifier = Modifier.fillMaxWidth())
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(section.size){index ->
                    CartTopic(section[index])
                }
            }
            NavigationLine(navController)
        }
    }


    @Composable
    fun CartTopic(nameSection: String) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                color = Color(0xFFC3CCFC),
                shape = RoundedCornerShape(10.dp)
            )
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp)),
        )
        {

            Row() {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.mechanika),
                    contentDescription = "Информация о приложении",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(20.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.Black
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = Color(0xFFCCFCC3),
                        shape = RoundedCornerShape(40.dp)
                    )){
                    Column(modifier = Modifier.padding(bottom =  5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Top) {
                            Button(onClick = { navController.navigate(Routes.ChooseLevel.route+"/$nameSection") },
                                shape = RoundedCornerShape(20.dp),  // округлая кнопка
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(100.dp),
                                contentPadding = PaddingValues(10.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                                    containerColor = Color(0x80D9D9D9))

                            ) {
                                Text(
                                    "Уровни",
                                    fontSize = 20.sp,
                                    fontFamily = goosberryFamily
                                )
                            }
                            Button(onClick = { navController.navigate(Routes.ChooseTrain.route+"/$nameSection") },
                                shape = RoundedCornerShape(20.dp),  // округлая кнопка
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(100.dp),
                                contentPadding = PaddingValues(10.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                                    containerColor = Color(0x80D9D9D9))

                            ) {
                                Text(
                                    modifier =Modifier.padding(0.dp),
                                    text ="Тренеровки",
                                    fontSize = 20.sp,
                                    fontFamily = goosberryFamily
                                )
                            }

                        }
                        Button(onClick = { navController.navigate(Routes.ChooseTeory.route+"/$nameSection") },
                            shape = RoundedCornerShape(20.dp),  // округлая кнопка
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .width(100.dp)
                                .padding(horizontal = 24.dp),

                            colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                                containerColor = Color(0x80D9D9D9))

                        ) {
                            Text(
                                text = "Теория \"$nameSection\"",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontFamily = goosberryFamily
                            )
                        }
                    }
                }

            }
        }
    }
}