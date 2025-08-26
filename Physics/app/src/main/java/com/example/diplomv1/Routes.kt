package com.example.diplomv1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object ChooseTopic : Routes("choose_topic")
    object Settings : Routes("settings")
    object ChooseTrain : Routes("choose_train")
    object ChooseLevel : Routes("choose_level")
    object ChooseRight : Routes("choose_right")
    object ChooseTeory : Routes("choose_teory")
    object Car : Routes("car")
}

