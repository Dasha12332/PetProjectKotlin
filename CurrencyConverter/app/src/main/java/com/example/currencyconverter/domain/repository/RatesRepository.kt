package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.domain.entity.CurrencyRate

interface RatesRepository {

    suspend fun getRates(baseCurrencyCode: String, amount: Double): List<CurrencyRate>
}
