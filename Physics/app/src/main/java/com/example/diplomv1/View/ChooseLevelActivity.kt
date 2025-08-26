package com.example.diplomv1.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diplomv1.Routes
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.ui.theme.goosberryFamily

class ChooseLevelActivity(private var navController: NavController, private var nameSection: String) {
    val listOfLists = (1..30)
        .chunked(3) { chunk -> chunk.map { it.toString() } }



    @Composable
    fun ChooseLevel() {
        Column(Modifier.background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopInfo()
            Button(onClick = { navController.navigate(Routes.ChooseTeory.route+"/$nameSection") },
                shape = RoundedCornerShape(20.dp),  // округлая кнопка
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0x80D9D9D9)
                )

            ) {
                Text(
                    "Посмотреть теорию",
                    fontSize = 30.sp,
                    fontFamily = goosberryFamily
                )
            }
            LazyColumn(modifier = Modifier.weight(1f)){
                items(listOfLists.size) { index ->
                    RowItems(buttonLabels = listOfLists[index])
                }
            }
            NavigationLine(navController)
        }

    }

//val buttonLabels = listOf("1", "2", "3")

    @Composable
    fun RowItems(modifier: Modifier = Modifier.fillMaxSize(), buttonLabels: List<String>) {
        Row(Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            buttonLabels.forEach { label ->
                Button(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),  // округлая кнопка
                    modifier = Modifier
                        .height(80.dp)
                        .width(90.dp)
                        .padding(5.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                        containerColor = Color(0xFFC3CCFC)
                    )

                ) {
                    Text(
                        text = label,
                        fontSize = 30.sp,
                        fontFamily = goosberryFamily
                    )
                }
            }

        }
    }




}








/*@Preview
@Composable
fun PreviewAppContent() {
    ChooseLevel()
}*/