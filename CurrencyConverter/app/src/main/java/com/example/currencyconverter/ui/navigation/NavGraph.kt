package com.example.currencyconverter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.currencyconverter.ui.screen.CurrenciesScreen
import com.example.currencyconverter.ui.screen.ExchangeScreen
import com.example.currencyconverter.ui.screen.TransactionsListScreen
import com.example.currencyconverter.ui.viewmodel.CurrenciesViewModel
import com.example.currencyconverter.ui.viewmodel.SharedViewModel
import com.example.currencyconverter.ui.viewmodel.factory.CurrenciesViewModelFactory
import java.net.URLDecoder

/*@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModelFactory: CurrenciesViewModelFactory
) {
    val navigator = remember { NavigatorImpl(navController) }

    // 👇 ViewModel создается один раз на уровне графа
    val sharedViewModel: CurrenciesViewModel = viewModel(factory = viewModelFactory)

    NavHost(
        navController = navController,
        startDestination = Destination.Currencies.route
    ) {
        composable(Destination.Currencies.route) {
            CurrenciesScreen(
                viewModelFactory = viewModelFactory,
                onExchangeClick = { baseCurrency, selectedCurrency ->
                    navigator.navigateToExchange(baseCurrency.code, selectedCurrency.code)
                },
                onConvertClick = {
                    // Optional
                }
            )
        }

        composable(
            route = Destination.Exchange.route,
            arguments = listOf(
                navArgument("buyCode") { defaultValue = "" },
                navArgument("sellCode") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val buyCode = backStackEntry.arguments?.getString("buyCode") ?: ""
            val sellCode = backStackEntry.arguments?.getString("sellCode") ?: ""

            val buyCurrency = sharedViewModel.currencies.find { it.code == buyCode }
            val sellCurrency = sharedViewModel.currencies.find { it.code == sellCode }

            if (buyCurrency != null && sellCurrency != null) {
                ExchangeScreen(
                    currencyCartBuy = buyCurrency,
                    currencyCartSell = sellCurrency
                )
            } else {
                navController.popBackStack()
            }
        }
    }
}*/

@Composable
fun AppNavGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = Destination.Currencies.route) {
        composable(Destination.Currencies.route) { CurrenciesScreen(sharedViewModel, navController) }
        composable(Destination.TransactionsList.route){ TransactionsListScreen() }
        composable(Destination.Exchange.route,
            arguments = listOf(
                navArgument("buyCode") { defaultValue = "" },
                navArgument("sellCode") { defaultValue = "" }))
        { backStackEntry ->
            val buyCode = backStackEntry.arguments?.getString("buyCode") ?: ""
            val sellCode = backStackEntry.arguments?.getString("sellCode") ?: ""

            val buyCurrency = sharedViewModel.currencies.find { it.code == buyCode }
            val sellCurrency = sharedViewModel.currencies.find { it.code == sellCode }

            ExchangeScreen(navController, currencyCartBuy = buyCurrency!!, currencyCartSell = sellCurrency!!)
        }
    }
}
