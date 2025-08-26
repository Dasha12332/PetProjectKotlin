package com.example.currencyconverter.ui.navigation

import com.example.currencyconverter.domain.entity.CurrencyCart

interface AppNavigator {
    fun navigateToCurrencies()
    fun navigateToExchange(buyCode: String, sellCode: String)
    fun navigateToTransactions()
}