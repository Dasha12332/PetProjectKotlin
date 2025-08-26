package com.example.diplomv1.View

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.diplomv1.ui.theme.DiplomV1Theme
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diplomv1.Routes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        setContent {
            DiplomV1Theme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = Color(0xFFC3E9FC)
                ) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}




@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) { HomeActivity(navController).FirstPage() }
        composable(Routes.ChooseTopic.route) { ChooseTopicActivity(navController).ChooseTopic() }
        composable(Routes.ChooseTrain.route+"/{sectionName}",
            arguments = listOf(navArgument("sectionName") { type = NavType.StringType })
        ) { backStackEntry ->
            val sectionName = backStackEntry.arguments?.getString("sectionName") ?: ""
            TrainActivity(navController, sectionName).TrainScreen()
        }
        composable(Routes.ChooseLevel.route+"/{sectionName}",
            arguments = listOf(navArgument("sectionName") { type = NavType.StringType })
        ) { backStackEntry ->
            val sectionName = backStackEntry.arguments?.getString("sectionName") ?: ""
            ChooseLevelActivity(navController, sectionName).ChooseLevel()
        }
        composable(Routes.ChooseTeory.route+"/{sectionName}",
            arguments = listOf(navArgument("sectionName") { type = NavType.StringType })
        ) { backStackEntry ->
            val sectionName = backStackEntry.arguments?.getString("sectionName") ?: ""
            ChooseTheoryActivity(navController, sectionName).VariantsTheoryScreen()
        }
        composable("${Routes.ChooseRight.route}/{mapJson}",
            arguments = listOf(navArgument("mapJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedMapJson = backStackEntry.arguments?.getString("mapJson") ?: ""
            val mapJson = URLDecoder.decode(encodedMapJson, "UTF-8")
            // Преобразуем JSON обратно в Map
            val type = object : TypeToken<Map<String, String>>() {}.type
            val dataMap: Map<String, String> = Gson().fromJson(mapJson, type)
            ChooseRightActivity(navController,  dataMap).ChooseRightScreen()
        }
        //composable(Routes.Car.route) { CarActivity(navController).CarSimulation() }
        composable(Routes.Car.route+"/{textTheory}",
            arguments = listOf(navArgument("textTheory") { type = NavType.StringType })
        ) { backStackEntry ->
            val textTheoryEncod = backStackEntry.arguments?.getString("textTheory") ?: ""
            val textTheory = URLDecoder.decode(textTheoryEncod, "UTF-8")
            CarActivity(navController, textTheory).CarSimulation()
        }

        composable(Routes.Settings.route) { SettingsActivity(navController).SettingsScreen() }

    }

}



