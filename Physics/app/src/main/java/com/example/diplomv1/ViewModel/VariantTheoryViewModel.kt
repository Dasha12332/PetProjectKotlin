package com.example.diplomv1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.diplomv1.Model.DataBase.SectionTheory
import com.example.diplomv1.Routes
import com.google.gson.Gson
import java.net.URLEncoder

class VariantTheoryViewModel: ViewModel() {
    var theoryTextList: List<String> = emptyList()
    var theoryNameList: List<String> = emptyList()
    var modelList:List<Long> = emptyList()


    fun initialize(theoryList: List<SectionTheory>) {
        theoryNameList = theoryList.map { it.nameTheory }
        theoryTextList = theoryList.map { it.textTheory }
        modelList = theoryList.mapNotNull { it.idPhysicModel }
    }

    fun goTo(index:Int, navController:NavController){
        val theoryJSON = Gson().toJson(theoryTextList[index])
        val encodedTheory = URLEncoder.encode(theoryJSON, "UTF-8")
        when (index) {
            1 -> navController.navigate(Routes.Car.route+"/${encodedTheory}")
        }
    }
}