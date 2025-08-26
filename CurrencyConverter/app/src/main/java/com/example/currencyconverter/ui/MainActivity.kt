package com.example.currencyconverter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.di.DependencyContainer
import com.example.currencyconverter.ui.navigation.AppNavGraph
import com.example.currencyconverter.ui.viewmodel.factory.CurrenciesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DependencyContainer.init(this)
        setContent {
            CurrencyConverterTheme {
                val navController = rememberNavController()
                //val viewModelFactory = CurrenciesViewModelFactory(application)

                AppNavGraph(
                    navController = navController
                    //viewModelFactory = viewModelFactory
                )
            }
        }
    }
}

