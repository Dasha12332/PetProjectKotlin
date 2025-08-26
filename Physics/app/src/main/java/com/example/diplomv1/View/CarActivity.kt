package com.example.diplomv1.View

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diplomv1.ViewModel.CarViewModel
import com.example.diplomv1.R
import com.example.diplomv1.View.Elements.TopInfo
import com.example.diplomv1.ui.theme.goosberryFamily
import com.example.diplomv1.View.Elements.Picker
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


import com.example.diplomv1.View.Elements.rememberPickerState
import com.example.diplomv1.com.example.diplomv1.NavigationLine
import kotlinx.coroutines.delay


class CarActivity (private var navController: NavController,private var textTheory: String) {


    @Composable
    fun CarSimulation() {
        val imageBitmap = ImageBitmap.imageResource(R.drawable.car2)
        val screenW = LocalConfiguration.current.screenWidthDp
        val screenWidthPx = with(LocalDensity.current) { screenW.dp.toPx() }
        val context = LocalContext.current
        val viewModel: CarViewModel = viewModel()
        val aPickerState = rememberPickerState()
        val pickerStateS = rememberPickerState()
        val tPickerState = rememberPickerState()
        val pickerStateU = rememberPickerState()
        val scope = rememberCoroutineScope()
        var xParam by remember { mutableStateOf("") }

        LaunchedEffect(viewModel.isPlaying) {
            if (viewModel.isPlaying) {
                val startTime = System.currentTimeMillis() - viewModel.elapsedMilliseconds
                while (viewModel.isPlaying) {
                    delay(10) // Обновляем каждые 10 мс для плавности
                    viewModel.updateElapsedTime(startTime)
                }
            }
        }

        val seconds = (viewModel.elapsedMilliseconds / 1000).toInt()
        val milliseconds = (viewModel.elapsedMilliseconds % 1000 / 10).toInt() // Берем только 2 цифры (сотые доли секунды)
        val formattedTime = String.format("%02d:%02d", seconds, milliseconds)

        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color(0xFFC3E9FC)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopInfo()
            Row(Modifier.fillMaxWidth()){
                Column(Modifier.weight(1f)) {
                    Text(text = "Время:$formattedTime", fontSize = 25.sp, fontFamily = goosberryFamily, textAlign = TextAlign.Center)
                    Box(Modifier.size(80.dp).padding(5.dp).align(Alignment.CenterHorizontally)){
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.speedometer),
                            contentDescription = "Информация о приложении",
                            contentScale = ContentScale.FillBounds)
                        // Второй слой (центр)
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .graphicsLayer {
                                    //rotationZ = animatedAngle
                                    // Точка вращения (0.5f, 0.9f = 50% по X, 90% по Y от размера стрелки)
                                    transformOrigin = TransformOrigin(0.5f, 0.9f)
                                }
                                .offset(x = -3.dp, y = -6.dp)
                        )
                    }

                    Text(text = "Вычесленный параметр: $xParam", fontSize = 25.sp, fontFamily = goosberryFamily, textAlign = TextAlign.Center)
                }
                Column(Modifier.weight(1f)) {
                    Row{
                        Text(text = "S = ", fontSize = 40.sp, fontFamily = goosberryFamily)

                        Picker(
                            state = pickerStateS,
                            items = viewModel.valuesS,
                            visibleItemsCount = 1,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp)
                                .background(color = Color(0x80AEBCC3)),
                            textModifier = Modifier.padding(0.dp),
                            textStyle = TextStyle(fontSize = 24.sp)
                        )
                        Text(text = "м", fontSize = 40.sp, fontFamily = goosberryFamily)
                    }
                    Row{
                        Text(text = "U = ", fontSize = 40.sp, fontFamily = goosberryFamily)

                        Picker(
                            state = pickerStateU,
                            items = viewModel.valuesU,
                            visibleItemsCount = 1,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp)
                                .background(color = Color(0x80AEBCC3)),
                            textModifier = Modifier.padding(0.dp),
                            textStyle = TextStyle(fontSize = 24.sp)
                        )
                        Text(text = "м/с", fontSize = 40.sp, fontFamily = goosberryFamily)
                    }
                    Row{
                        Text(text = "t = ", fontSize = 40.sp, fontFamily = goosberryFamily)
                        Picker(
                            state = tPickerState,
                            items = viewModel.tValues,
                            visibleItemsCount = 1,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp)
                                .background(color = Color(0x80AEBCC3)),
                            textModifier = Modifier.padding(0.dp),
                            textStyle = TextStyle(fontSize = 24.sp)
                        )
                        Text(text = "с", fontSize = 40.sp, fontFamily = goosberryFamily)
                    }
                    Row{
                        Text(text = "a = ", fontSize = 40.sp, fontFamily = goosberryFamily)
                        Picker(
                            state = aPickerState,
                            items = viewModel.aValues,
                            visibleItemsCount = 1,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp)
                                .background(color = Color(0x80AEBCC3)),
                            textModifier = Modifier.padding(0.dp),
                            textStyle = TextStyle(fontSize = 24.sp)
                        )
                        Text( text = "м/c²", fontSize = 40.sp, fontFamily = goosberryFamily)
                    }
                }

            }
            // Дорога с машинкой
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(10.dp)
            ) {
                // Дорога
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // Асфальт
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(0f, size.height / 2 - 30f),
                        size = Size(size.width, 2f)
                    )
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(0f, size.height / 2 + 30f),
                        size = Size(size.width, 2f)
                    )

                    // Разметка
                    for (i in 0..(size.width / 100).toInt()) {
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(i * 100f + 20f, size.height / 2 - 1f),
                            size = Size(60f, 2f)
                        )
                    }
                    // Машинка
                    drawImage(
                        image = imageBitmap,
                        dstOffset = IntOffset((viewModel.carPosition.value * size.width / viewModel.distance).toInt(), (size.height / 2 -80).toInt()),
                        dstSize = IntSize(135, 123)
                    )
                }

            }
            // Кнопка управления
            Button(
                onClick = {
                    if (viewModel.isPlaying){
                        viewModel.resetAnimation(scope)
                        return@Button
                    }
                    val masParams = viewModel.checkAllState(strS = pickerStateS.selectedItem, aStr = aPickerState.selectedItem,
                        strU0 = pickerStateU.selectedItem, tStr = tPickerState.selectedItem,
                        onError = { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        },
                        x = {x -> xParam = x})
                    viewModel.startAnimation(masParams, screenWidthPx, imageBitmap.width, scope)
                },
                modifier = Modifier
                    .width(125.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black,
                    containerColor = Color(0xFFC3CCFC)
                ),
                shape = RoundedCornerShape(20.dp),

                ) {
                Text(text =  if (viewModel.isPlaying) "СБРОС" else "СТАРТ", fontFamily = goosberryFamily, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = textTheory,
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(end = 10.dp, start = 10.dp, bottom = 10.dp, top = 0.dp)
                    .background(color = Color(0xFFC3CCFC), shape = RoundedCornerShape(20.dp))
                    .padding(5.dp),
                fontSize = 22.sp,
                fontFamily = goosberryFamily
            )

            NavigationLine(navController)
        }

    }

    /*@Preview
    @Composable
    fun PreviewAppContent() {
        CarSimulation()
    }*/
}



