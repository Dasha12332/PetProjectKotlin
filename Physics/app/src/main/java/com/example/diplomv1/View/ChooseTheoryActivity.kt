package com.example.diplomv1.View

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.diplomv1.Routes
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.ViewModel.DataBaseViewModel
import com.example.diplomv1.ViewModel.DataBaseViewModelFactory
import com.example.diplomv1.ViewModel.VariantTheoryViewModel
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.ui.theme.goosberryFamily
import kotlinx.coroutines.launch

class ChooseTheoryActivity(private var navController: NavController, private val sectionName: String) {

    @Composable
    fun VariantsTheoryScreen() {
        val context = LocalContext.current
        val viewModelData: DataBaseViewModel = viewModel(
            factory = DataBaseViewModelFactory(context.applicationContext as Application)
        )
        val viewModelTheory: VariantTheoryViewModel = viewModel()

        LaunchedEffect(Unit) {
            val theory = viewModelData.getTheory(sectionName)
            viewModelTheory.initialize(theory)
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopInfo()
            Text(
                text = "$sectionName",
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontFamily = goosberryFamily
            )
            LazyColumn(modifier = Modifier.weight(1f)){
                items(viewModelTheory.theoryNameList.size){index ->
                    Button(onClick = {viewModelTheory.goTo(viewModelTheory.modelList[index].toInt(), navController) },
                        shape = RoundedCornerShape(20.dp),  // округлая кнопка
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 10.dp),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                            containerColor = Color(0xFFC3CCFC)
                        )
                    ) {
                        Text(viewModelTheory.theoryNameList[index], fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = goosberryFamily
                        )}

                }
            }

            NavigationLine(navController)
        }
    }
}
