package com.example.diplomv1.View

import android.app.Application
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.diplomv1.Model.DataBase.AppDatabase
import com.example.diplomv1.Routes
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.ViewModel.DataBaseViewModel
import com.example.diplomv1.ViewModel.DataBaseViewModelFactory
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.ui.theme.goosberryFamily
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URLEncoder


class TrainActivity(private var navController: NavController, private var nameSection:String) {


    @Composable
    fun TrainScreen() {
        val context = LocalContext.current
        val viewModel: DataBaseViewModel = viewModel(
            factory = DataBaseViewModelFactory(context.applicationContext as Application)
        )
        val scope = rememberCoroutineScope()

        Column(Modifier.fillMaxSize().background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TopInfo(modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                scope.launch {
                    val result = viewModel.getUnitPair(nameSection)
                    val mapJson = Gson().toJson(result)
                    val encodedMapJson = URLEncoder.encode(mapJson, "UTF-8")
                    navController.navigate(Routes.ChooseRight.route+"/$encodedMapJson")
                } },
                shape = RoundedCornerShape(40.dp),  // округлая кнопка
                modifier = Modifier.height(150.dp).fillMaxWidth()
                    .padding(top = 30.dp, start = 20.dp, end =20.dp),
                contentPadding = PaddingValues(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0xFFFFFFFF))

            ) {
                Text(
                    "Еденицы измерения",
                    fontSize = 30.sp,
                    fontFamily = goosberryFamily
                )
            }
            Button(onClick = {scope.launch {
                val result = viewModel.getLetterPair(nameSection)
                val mapJson = Gson().toJson(result)
                val encodedMapJson = URLEncoder.encode(mapJson, "UTF-8")
                navController.navigate(Routes.ChooseRight.route+"/$encodedMapJson")
            } },
                shape = RoundedCornerShape(40.dp),  // округлая кнопка
                modifier = Modifier.height(140.dp).fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end =20.dp),
                border = BorderStroke(1.dp, Color.Black),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0xFFC3CCFC))

            ) {
                Text(
                    "Обозначения",
                    fontSize = 30.sp,
                    fontFamily = goosberryFamily
                )
            }
            Button(onClick = { scope.launch {
                val result = viewModel.getFormulaPair(nameSection)
                val mapJson = Gson().toJson(result)
                val encodedMapJson = URLEncoder.encode(mapJson, "UTF-8")
                navController.navigate(Routes.ChooseRight.route+"/$encodedMapJson")
            } },
                shape = RoundedCornerShape(40.dp),  // округлая кнопка
                modifier = Modifier.height(140.dp).fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end =20.dp),
                contentPadding = PaddingValues(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0xFFFFFFFF))

            ) {
                Text(
                    "Формулы",
                    fontSize = 30.sp,
                    fontFamily = goosberryFamily
                )
            }
            Box(modifier = Modifier.weight(1f))
            NavigationLine(navController)
        }
    }
}