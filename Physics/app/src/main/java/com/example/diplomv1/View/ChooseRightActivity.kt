package com.example.diplomv1.View

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.ViewModel.ChooseRightViewModel
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import com.example.diplomv1.ui.theme.goosberryFamily
import kotlinx.coroutines.delay

class ChooseRightActivity(private var navController: NavController, private var map: Map<String, String>) {

    @Composable
    fun ChooseRightScreen() {
        val viewModel: ChooseRightViewModel = viewModel()
        val scope = rememberCoroutineScope()
        // Таймер
        LaunchedEffect(!viewModel.isCompleted) {
            while (!viewModel.isCompleted) {
                delay(1000)
                viewModel.elapsedSeconds += 1
            }
        }

        LaunchedEffect(Unit) {
            viewModel.initialize(map)
        }

        val minutes = viewModel.elapsedSeconds / 60
        val seconds = viewModel.elapsedSeconds % 60
        val formattedTime = String.format("%d:%02d", minutes, seconds)


        Column(Modifier.background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,) {
            TopInfo()
            Text(
                modifier=Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                text = "Потраченное время: $formattedTime",
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                fontFamily = goosberryFamily
            )

            for (i in viewModel.leftList.indices) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MatchingButton(
                        modifier = Modifier
                            .height(90.dp)
                            .padding(10.dp)
                            .weight(1f),
                        text = viewModel.leftList[i],
                        state = viewModel.leftStates[i]
                    ) { viewModel.onLeftClick(i, scope) }
                    MatchingButton(
                        modifier = Modifier
                            .height(90.dp)
                            .padding(10.dp)
                            .weight(1f),
                        text = viewModel.rightList[i],
                        state = viewModel.rightStates[i]
                    ) { viewModel.onRightClick(i, scope) }
                }

                //Spacer(modifier = Modifier.height(8.dp))
            }


            Box(modifier = Modifier.weight(1f))
            NavigationLine(navController)
        }
    }

    enum class ButtonState { Normal, Selected, MatchedSuccess, Matched, Error }
    @Composable
    fun MatchingButton(
        modifier: Modifier,
        text: String,
        state: ButtonState,
        onClick: () -> Unit
    ) {

        val bgColor by animateColorAsState(
            targetValue = when (state) {
                ButtonState.Normal -> Color.White
                ButtonState.Selected -> Color(0x885E35B1)
                ButtonState.MatchedSuccess -> Color(0xFF66BB6A) // зелёный
                ButtonState.Matched -> Color.Gray
                ButtonState.Error -> Color(0xFFFF6B6B)
            },
            label = "bg"
        )

        Button(
            onClick = onClick,
            shape = RoundedCornerShape(40.dp),
            modifier = modifier,
            contentPadding = PaddingValues(10.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = Color.Black
            ),
            enabled = state != ButtonState.Matched

        ) {
            Text(text = text, textAlign = TextAlign.Center, fontSize = 22.sp, /*fontFamily = goosberryFamily*/)
        }
    }
}