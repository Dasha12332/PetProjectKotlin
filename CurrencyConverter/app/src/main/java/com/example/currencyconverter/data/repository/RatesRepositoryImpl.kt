package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.dataSource.remote.RatesService
import com.example.currencyconverter.data.mapper.RateMapper
import com.example.currencyconverter.domain.entity.CurrencyRate
import com.example.currencyconverter.domain.repository.RatesRepository

class RatesRepositoryImpl(
    private val ratesService: RatesService,
    private val mapper: RateMapper
) : RatesRepository {

    override suspend fun getRates(baseCurrencyCode: String, amount: Double): List<CurrencyRate> {
        return ratesService.getRates(baseCurrencyCode, amount)
            .map { mapper.toEntity(it) }
    }
}

