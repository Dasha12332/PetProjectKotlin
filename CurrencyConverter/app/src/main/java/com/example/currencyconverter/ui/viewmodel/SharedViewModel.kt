package com.example.currencyconverter.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.domain.entity.CurrencyCart

class SharedViewModel:ViewModel() {
    var currencies = mutableStateListOf<CurrencyCart>()
}