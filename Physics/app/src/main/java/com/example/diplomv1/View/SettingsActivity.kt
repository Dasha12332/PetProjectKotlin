package com.example.diplomv1.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class SettingsActivity(private var navController: NavController) {


    @Composable
    fun SettingsScreen(modifier: Modifier = Modifier.fillMaxSize()) {

        val checkedState1 = remember { mutableStateOf(true) }
        val checkedState2 = remember { mutableStateOf(true) }
        val checkedState3 = remember { mutableStateOf(true) }

        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TopInfo(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(15.dp))
            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp)){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.notification),
                    contentDescription = "Информация о приложении",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Уведомления",
                    fontSize = 38.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 7.dp, horizontal = 15.dp),
                    fontFamily = goosberryFamily
                )
                Switch(
                    checked = checkedState1.value,
                    onCheckedChange = { checkedState1.value = it }
                )
            }

            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp)){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.music),
                    contentDescription = "Информация о приложении",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Музыка",
                    fontSize = 38.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 7.dp, horizontal = 15.dp),
                    fontFamily = goosberryFamily
                )
                Switch(
                    checked = checkedState2.value,
                    onCheckedChange = { checkedState2.value = it }
                )
            }

            Row(Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp)){
                Icon(
                    modifier =Modifier.padding(vertical = 5.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.sound),
                    contentDescription = "Информация о приложении",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Звук",
                    fontSize = 38.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 0.dp, horizontal = 15.dp),
                    fontFamily = goosberryFamily
                )
                Switch(
                    checked = checkedState3.value,
                    onCheckedChange = { checkedState3.value = it }
                )
            }


            Spacer(modifier = Modifier.weight(1f))
            NavigationLine(navController)
        }
    }

}






/*@Preview
@Composable
fun PreviewAppContent() {
    SettingsScreen()
}*/