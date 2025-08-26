package com.example.diplomv1.View.Elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diplomv1.R
import com.example.diplomv1.ui.theme.goosberryFamily

@Composable
fun TopInfo(modifier: Modifier = Modifier.fillMaxSize()) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top){

            IconButton(onClick ={}, Modifier.size(80.dp)){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.lvlimage),
                    contentDescription = "Информация о приложении",
                    modifier = Modifier.width(68.dp).height(66.dp),
                    tint = Color.Black
                )
                Text(
                    text = "12",
                    fontSize=38.sp,
                    fontFamily = goosberryFamily,
                    modifier = Modifier.padding(top = 2.dp).padding(end = 1.dp)
                )
            }

        IconButton(onClick ={},Modifier.size(80.dp)){
            Row{
                Text(
                    text = "9",
                    fontSize=52.sp,
                    fontFamily = goosberryFamily,
                    modifier = Modifier,
                    color = Color(0xFFEA5353)

                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.imagehp),
                    contentDescription = "Информация о приложении",
                    modifier = Modifier.size(40.dp).padding(top = 7.dp),
                    tint = Color.Unspecified
                )
            }

        }
        IconButton(onClick ={},Modifier.size(80.dp)){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.imageavatar),
                contentDescription = "Информация о приложении",
                modifier = Modifier.size(50.dp),
                tint = Color.Black
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TopInfoPreview() {
    TopInfo(Modifier)
}
