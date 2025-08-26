package com.example.currencyconverter.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.example.currencyconverter.data.dataSource.remote.RemoteRatesServiceImpl
import com.example.currencyconverter.data.repository.AccountRepositoryImpl
import com.example.currencyconverter.domain.entity.Currency
import com.example.currencyconverter.domain.entity.CurrencyCart
import com.example.currencyconverter.ui.mapper.StringMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode



class CurrenciesViewModel(
    private val accountRepository: AccountRepositoryImpl
) : ViewModel() {

    private val remoteService = RemoteRatesServiceImpl()
    private var strMapper =StringMapper()

    var currencies = mutableStateListOf<CurrencyCart>()
    private var originalCurrencyList: List<CurrencyCart> = emptyList()

    init {
        viewModelScope.launch {
            val accounts = accountRepository.getAccounts()
            val accountMap = accounts.associateBy { it.currencyCode }

            val initialList = Currency.values().map { currency ->
                val code = currency.name
                val balance = accountMap[code]?.amount ?: 0.0
                CurrencyCart(
                    code = code,
                    name = strMapper.getResCurrencyId(code),
                    symbol = strMapper.getResSymbolId(code),
                    flag = strMapper.getResHttpId(code),
                    balance = roundTo(balance,2),
                    rate = 1.0,
                    additionalRate = 0.0,
                    inputAmount = null
                )
            }

            originalCurrencyList = initialList
            currencies.addAll(initialList)

            startRateUpdates()

        }
    }

    fun selectCurrency(index: Int) {
        if (index in currencies.indices) {
            val selected = currencies.removeAt(index)
            val updated = selected.copy(rate = 1.0)
            currencies.add(0, updated)
        }
        viewModelScope.launch {
            updateRates()
        }

    }

    private fun startRateUpdates() {
        viewModelScope.launch {
            while (true) {
                updateRates()
                delay(1000L) // Обновлять каждую секунду
            }
        }
    }

    fun roundTo(value: Double, count:Int): Double {
        return BigDecimal(value)
            .setScale(count, RoundingMode.HALF_UP)
            .toDouble()
    }

    private suspend fun updateRates() {
        if (currencies.isEmpty()) return

        val baseCurrency = currencies[0]
        val baseCode = baseCurrency.code
        val baseAmount = baseCurrency.inputAmount ?: 1.0

        try {
            val rateDtos = remoteService.getRates(baseCode, baseAmount)

            // Преобразуем список RateDto в Map для быстрого доступа по коду валюты
            val rateMap = rateDtos.associateBy { it.currency }

            // Обновляем значения в currencies
            currencies.forEachIndexed { index, oldCurrency ->
                val newRate = rateMap[oldCurrency.code]?.value
                if (newRate != null) {
                    currencies[index] = if (index == 0) {
                        oldCurrency
                    } else {
                        oldCurrency.copy(rate = roundTo(newRate,5))
                    }
                }
            }
            Log.d("my tag", "$currencies")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateBaseAmount(amount: Double) {
        if (currencies.isEmpty()) return

        val base = currencies[0]
        currencies[0] = base.copy(inputAmount = amount)

        filterCurrenciesByBalance()
    }

    private fun filterCurrenciesByBalance() {
        if (currencies.isEmpty()) return

        val base = currencies[0]
        val inputAmount = base.inputAmount ?: return

        // Возвращаем обратно первую валюту (базу), она всегда остаётся
        val filtered = mutableListOf(base)

        // Сравниваем доступность других валют
        for (i in 0 until originalCurrencyList.size) {
            val currency = originalCurrencyList[i]
            val exchangeRate = currency.rate ?: 0.0
            val moneyNeed = exchangeRate*inputAmount

            if (currency.balance!! >= moneyNeed&&base.name!=currency.name) {
                filtered.add(currency)
            }
        }

        currencies.clear()
        currencies.addAll(filtered)
    }

    fun resetCurrencies() {
        if (originalCurrencyList.isNotEmpty()) {
            currencies.clear()
            currencies.addAll(originalCurrencyList)
        }
    }
}