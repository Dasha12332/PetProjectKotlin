package com.example.currencyconverter.ui.navigation

import androidx.navigation.NavHostController
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.CurrencyCart
import javax.inject.Inject

class NavigatorImpl(private val navController: NavHostController) : AppNavigator {

    override fun navigateToCurrencies() {
        navController.navigate(Destination.Currencies.route)
    }

    override fun navigateToExchange(buyCode: String, sellCode: String) {
        navController.navigate(Destination.Exchange.createRoute(buyCode, sellCode))
    }

    override fun navigateToTransactions() {
        navController.navigate(Destination.TransactionsList.route)
    }

}